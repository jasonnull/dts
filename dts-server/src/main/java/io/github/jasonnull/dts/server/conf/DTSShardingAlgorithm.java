package io.github.jasonnull.dts.server.conf;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.ListShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.hint.HintShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DTSShardingAlgorithm {
    @Slf4j
    /**
     * 单分片规则，精确分片规则（离散映射）：这种情况下是精确匹配，这种情况下避免了全表查询，但是这种情况下between and 必中全表查询
     */
    public static class DTSDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
        @Override
        public String doSharding(Collection<String> collection, PreciseShardingValue<Long> shardingValue) {
            for (String name : collection) {
                // 未来这边可以自实现相关分片规则，当id超过多少时可以分到其他的库中
                if (name.endsWith(shardingValue.getValue() % collection.size() + "")) {
                    return name;
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    @Slf4j
    /**
     * 单分片规则，精确分片规则（离散映射）：这种情况下是精确匹配，这种情况下避免了全表查询，但是这种情况下between and 必中全表查询
     */
    public static class DTSTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
        @Override
        public String doSharding(Collection<String> collection, PreciseShardingValue<Long> shardingValue) {
            for (String name : collection) {
                // 未来这边可以自实现相关分片规则，当id超过多少时可以分到其他的表中，对表做到可扩充
                if (name.endsWith(shardingValue.getValue() % collection.size() + "")) {
                    return name;
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    @Slf4j
    /**
     * 单分片规则，区间分片规则（连续映射）：这种情况下between and 效率上升，但是数据分布不均匀，热点问题
     */
    public static class DTSRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
        @Override
        public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
            Collection<String> collect = new ArrayList<>();
            Range<Long> valueRange = rangeShardingValue.getValueRange();
            // 根据between在什么区间内，返回不同的集合（日期，id都可）
            for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
                for (String each : collection) {
                    if (each.endsWith(i % collection.size() + "")) {
                        collect.add(each);
                    }
                }
            }
            return collect;
        }
    }

    @Slf4j
    /**
     * 复杂分片规则
     */
    public static class DTSComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm {

        @Override
        /**
         * 连续分片（RangeShardingValue），离散分片（ListShardingValue），这两种情况不会同时存在
         */
        public Collection<String> doSharding(Collection<String> collection, Collection<ShardingValue> shardingValues) {
            ShardingValue shardingValue = shardingValues.iterator().next();
            List<String> shardingSuffix = new ArrayList<>();
            /**
             * 例如：根据job_group + job_id 双分片键来进行分表
             * */
            if (shardingValue instanceof ListShardingValue) {
                Collection<Long> jobGroupValues = getListShardingValue(shardingValues, "job_group");
                Collection<Long> jobIdValues = getListShardingValue(shardingValues, "job_id");
                for (Long jobGroup : jobGroupValues) {
                    for (Long jobId : jobIdValues) {
                        String suffix = jobGroup % 4 + "_" + jobId % 2;
                        collection.forEach(x -> {
                            if (x.endsWith(suffix)) {
                                shardingSuffix.add(x);
                            }
                        });
                    }
                }
            } else {
                Range<Long> jobGroupValues = getRangeShardingValue(shardingValues, "job_group");
                Range<Long> jobIdValues = getRangeShardingValue(shardingValues, "job_id");
                for (Long jobGroup = jobGroupValues.lowerEndpoint(); jobGroup <= jobGroupValues.upperEndpoint(); jobGroup++) {
                    for (Long jobId = jobIdValues.lowerEndpoint(); jobId <= jobIdValues.upperEndpoint(); jobId++) {
                        String suffix = jobGroup % 4 + "_" + jobId % 2;
                        collection.forEach(x -> {
                            if (x.endsWith(suffix)) {
                                shardingSuffix.add(x);
                            }
                        });
                    }
                }
            }
            return shardingSuffix;
        }

        /**
         * 获取离散分片时对应的键值数据
         *
         * @param shardingValues
         * @param key
         * @return
         */
        private Collection<Long> getListShardingValue(Collection<ShardingValue> shardingValues, String key) {
            Iterator<ShardingValue> iterator = shardingValues.iterator();
            while (iterator.hasNext()) {
                ListShardingValue value = (ListShardingValue) iterator.next();
                if (value.getColumnName().equals(key)) {
                    return value.getValues();
                }
            }
            throw new UnsupportedOperationException();
        }

        /**
         * 获取连续分片时对应的键值数据
         *
         * @param shardingValues
         * @param key
         * @return
         */
        private Range<Long> getRangeShardingValue(Collection<ShardingValue> shardingValues, String key) {
            Iterator<ShardingValue> iterator = shardingValues.iterator();
            while (iterator.hasNext()) {
                RangeShardingValue value = (RangeShardingValue) iterator.next();
                if (value.getColumnName().equals(key)) {
                    return value.getValueRange();
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    @Slf4j
    /**
     * 用来强制制定访问哪一个数据库的
     */
    public static class DTSHintDatasourceShardingAlgorithm implements HintShardingAlgorithm {
        @Override
        public Collection<String> doSharding(Collection<String> collection, ShardingValue shardingValue) {
            List<String> shardingResult = new ArrayList<>();
            collection.forEach(targetName -> {
                String suffix = targetName.substring(targetName.lastIndexOf("_") + 1);
                if (StringUtils.isNumeric(suffix)) {
                    /**
                     *  hint分片算法的ShardingValue有两种具体类型:
                     *  ListShardingValue和RangeShardingValue，取决于
                     *  operator的类型  io.shardingjdbc.core.constant.ShardingOperator
                     */
                    ListShardingValue<Integer> tmpSharding = (ListShardingValue<Integer>) shardingValue;
                    tmpSharding.getValues().forEach(value -> {
                        if (value % 4 == Integer.parseInt(suffix)) {
                            shardingResult.add(targetName);
                        }
                    });
                }
            });
            return shardingResult;
        }
    }

    @Slf4j
    /**
     * 用来强制制定访问哪一个表的
     */
    public static class DTSHintTableShardingAlgorithm implements HintShardingAlgorithm {

        @Override
        public Collection<String> doSharding(Collection<String> collection, ShardingValue shardingValue) {
            List<String> shardingResult = new ArrayList<>();
            collection.forEach(collectionName -> {
                String suffix = collectionName.substring(collectionName.lastIndexOf("_") + 1);
                if (StringUtils.isNumeric(suffix)) {
                    /**
                     *  hint分片算法的ShardingValue有两种具体类型:
                     *  ListShardingValue和RangeShardingValue，取决于
                     *  operator的类型  io.shardingjdbc.core.constant.ShardingOperator
                     */
                    ListShardingValue<Integer> tmpSharding = (ListShardingValue<Integer>) shardingValue;
                    tmpSharding.getValues().forEach(value -> {
                        if (value % 2 == Integer.parseInt(suffix)) {
                            shardingResult.add(collectionName);
                        }
                    });
                }
            });
            return shardingResult;
        }
    }
}
