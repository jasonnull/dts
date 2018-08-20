package io.github.jasonnull.dts.server.conf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.ListShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DTSShardingAlgorithm {
    @Slf4j
    public static class DTSDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
        @Override
        public String doSharding(Collection<String> collection, PreciseShardingValue<Long> shardingValue) {
            log.info("collection:" + JSON.toJSONString(collection) + ",shardingValue:" + JSON.toJSONString(shardingValue));
            for (String name : collection) {
                // 未来这边可以自实现相关分片规则，当id超过多少时可以分到其他的库中
                if (name.endsWith(shardingValue.getValue() % collection.size() + "")) {
                    log.info("return name:" + name);
                    return name;
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    @Slf4j
    public static class DTSTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
        @Override
        public String doSharding(Collection<String> collection, PreciseShardingValue<Long> shardingValue) {
            log.info("collection:" + JSON.toJSONString(collection) + ",shardingValue:" + JSON.toJSONString(shardingValue));
            for (String name : collection) {
                // 未来这边可以自实现相关分片规则，当id超过多少时可以分到其他的表中，对表做到可扩充
                if (name.endsWith(shardingValue.getValue() % collection.size() + "")) {
                    log.info("return name:" + name);
                    return name;
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    @Slf4j
    public static class DTSComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm {

        @Override
        public Collection<String> doSharding(Collection<String> collection, Collection<ShardingValue> shardingValues) {
            log.info("collection:" + JSONObject.toJSONString(collection) + ",shardingValues:" + JSONObject.toJSONString(shardingValues));

            Collection<Long> jobGroupValues = getShardingValue(shardingValues, "job_group");
            Collection<Long> jobIdValues = getShardingValue(shardingValues, "job_id");
            List<String> shardingSuffix = new ArrayList<>();
            /**
             * 例如：根据job_group + job_id 双分片键来进行分表
             * */
            for (Long jobGroupVal : jobGroupValues) {
                for (Long jobIdVal : jobIdValues) {
                    String suffix = jobGroupVal % 4 + "_" + jobIdVal % 2;
                    collection.forEach(x -> {
                        if (x.endsWith(suffix)) {
                            shardingSuffix.add(x);
                        }
                    });
                }
            }

            return shardingSuffix;
        }

        private Collection<Long> getShardingValue(Collection<ShardingValue> shardingValues, final String key) {
            Collection<Long> valueSet = new ArrayList<>();
            Iterator<ShardingValue> iterator = shardingValues.iterator();
            while (iterator.hasNext()) {
                ShardingValue next = iterator.next();
                if (next instanceof ListShardingValue) {
                    ListShardingValue value = (ListShardingValue) next;
                    /**
                     * 例如：根据job_group + job_id 双分片键来进行分表（暂时没用）
                     * */
                    if (value.getColumnName().equals(key)) {
                        return value.getValues();
                    }
                }
            }
            return valueSet;
        }
    }

    @Slf4j
    public static class DTSRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

        @Override
        public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
            log.info("Range collection:" + JSON.toJSONString(collection) + ",rangeShardingValue:" + JSON.toJSONString(rangeShardingValue));
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
}
