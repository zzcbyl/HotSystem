<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>设备信息表管理</title>
    <#include "../shared/cssFile.ftl" />
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <#include "../shared/topBar.ftl" />
        <#include "../shared/leftMenu.ftl" />
        <div class="right_col" role="main">
            <div id="content">
                <div id="content-header">
                    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
                        Home</a> <a class="current" href="#">设备信息表管理</a></div>
                </div>
                <hr/>
                <div class="container-fluid">
                    <form id="search_form" class="form-inline" style="margin-bottom: 10px;">

                        <div class="form-group">
                            <label>查询：</label>
                            <input type="text" class="form-control" name="searchDeviceSN" placeholder="设备编号">　
                        </div>

                        <div class="form-group">
                            <label>信号强度：</label>
                            <input type="text" style="width: 80px;" class="form-control" id="searchLevel1" name="searchLevel1" placeholder="信号强度">－
                            <input type="text" style="width: 80px;" class="form-control" id="searchLevel2" name="searchLevel2" placeholder="信号强度">　
                        </div>

                        <button id="search" type="button" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索
                        </button>
                    </form>
                    <div id="maingrid" style="margin:0; padding:0"></div>

                </div>
            </div>
        </div>
        <#include "../shared/footer.ftl" />
    </div>
</div>
<#include "../shared/footerScript.ftl" />
<script src="/js/bootstrap-maxlength.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/bussiness/deviceManage.js" type="text/javascript"></script>
</body>
</html>
