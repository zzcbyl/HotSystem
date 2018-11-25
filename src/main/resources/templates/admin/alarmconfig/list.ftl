<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>报警配置</title>
    <#include "../shared/cssFile.ftl" />
    <link href="/js/colpick/css/colpick.css" rel="stylesheet">
    <style type="text/css">
        .modal-body .form-group label{
            width: 120px;
        }
    </style>
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
                        Home</a> <a class="current" href="#">报警配置</a></div>
                </div>
                <hr/>
                <div id="FormModel" class="container-fluid">
                    <form style="padding: 20px 30px 50px; width: 1000px; background: #fff;">
                        <div class="modal-body">
                            <input type="hidden" name="id" value="0"/>
                            <div class="form-group">
                                <label>低报警：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="lowAlarm1" placeholder="低报警">
                                <label>低报警颜色：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="color_low1" placeholder="低报警颜色">
                            </div>
                            <div class="form-group">
                                <label>低低报警：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="lowAlarm2" placeholder="低低报警">
                                <label>低低报警颜色：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="color_low2" placeholder="低低报警颜色">
                            </div>
                            <div class="form-group">
                                <label>高报警：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="highAlarm1" placeholder="高报警">
                                <label>高报警颜色：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="color_High1" placeholder="高报警颜色">
                            </div>
                            <div class="form-group">
                                <label>高高报警：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="highAlarm2" placeholder="高高报警">
                                <label>高高报警颜色：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="color_High2" placeholder="高高报警颜色">
                            </div>
                            <div class="form-group">
                                <label>正常颜色：</label>
                                <input style="width:230px; display: inline;" type="text" class="form-control"
                                       name="color_Normal" placeholder="正常颜色">　
                            </div>

                            <button style="margin-top: 10px;" type="button" class="btn btn-primary blue submit"><i class="fa fa-save"></i>
                                <span>保存</span></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
        <#include "../shared/footer.ftl" />
</div>
</div>
<#include "../shared/footerScript.ftl" />
<script src="/js/colpick/js/colpick.js"></script>
<script src="/js/bootstrap-maxlength.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/bussiness/alarmconfigManage.js" type="text/javascript"></script>
</body>
</html>
