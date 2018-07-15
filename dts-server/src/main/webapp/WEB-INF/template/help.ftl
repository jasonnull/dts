<!DOCTYPE html>
<html>
<head>
      <#import "/common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <title>${I18n.admin_name}</title>
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && "off" == cookieMap["xxljob_adminlte_settings"].value >sidebar-collapse</#if> ">
<div class="wrapper">
    <!-- header -->
    <@netCommon.commonHeader />
    <!-- left -->
    <@netCommon.commonLeft "help" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>${I18n.job_help}</h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="callout callout-info">
                <h4>${I18n.admin_name_full}</h4>
                <br>
                <p>
                    frameborder="0" scrolling="0" width="170px" height="20px"
                    style="margin-bottom:-5px;"></iframe>
                    <br><br>
                    <br><br>

                </p>
                <p></p>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- footer -->
    <@netCommon.commonFooter />
</div>
<@netCommon.commonScript />
</body>
</html>
