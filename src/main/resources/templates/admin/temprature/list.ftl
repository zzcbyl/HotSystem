<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看温度信息</title>
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
                        Home</a> <a class="current" href="#">查看温度信息</a></div>
                </div>
                <hr/>
                <div class="container-fluid">
                    <form id="search_form" class="form-inline" style="margin-bottom: 10px;">
                        <div>
                            <div class="form-group">
                                <label>查询：</label>
                                <select id="searchCompanyID" name="searchCompanyID" class="form-control">
                                    <option value="-1">--请选择公司--</option>
                                </select>　
                            </div>
                            <div class="form-group">
                                <select id="searchStationID" name="searchStationID" class="form-control">
                                    <option value="-1">--请选择换热站--</option>
                                </select>　
                            </div>
                            <div class="form-group">
                                <select id="searchCommunityID" name="searchCommunityID" class="form-control">
                                    <option value="-1">--请选择小区--</option>
                                </select>　
                            </div>
                            <div class="form-group">
                                <select id="searchBuildingID" name="searchBuildingID" class="form-control">
                                    <option value="-1">--请选择楼号--</option>
                                </select>　
                            </div>
                            <div class="form-group">
                                <select id="searchRoomID" name="searchRoomID" class="form-control">
                                    <option value="-1">--请选择房间位置--</option>
                                </select>　
                            </div>

                        </div>
                        <div>
                            <div class="form-group">
                                <label>　　　</label>
                                <input type="text" style="width: 100px;" class="form-control" name="searchPositionID"
                                       placeholder="测温点编号">　
                                <input type="text" style="width: 100px;" class="form-control" name="searchDeviceSN"
                                       placeholder="设备编号">　
                            </div>
                            <div class="form-group">
                                <input type="text" style="width: 120px;" class="form-control" name="searchCustomerName"
                                       placeholder="住户姓名">　
                            </div>
                            <div class="form-group">
                                <input type="text" style="width: 120px;" class="form-control" name="searchPhoneNumber"
                                       placeholder="住户电话">　
                            </div>
                            <div class="form-group">
                                <label>温度范围：</label>
                                <input type="text" style="width: 60px;" class="form-control" name="searchMinTemp" /> －
                                <input type="text" style="width: 60px;" class="form-control" name="searchMaxTemp" />　　　
                            </div>

                        <#--<div class="form-group">
                            <input type="text" style="width: 120px;" class="form-control" name="searchAddTime1"
                                   placeholder="开始时间"> －
                            <input type="text" style="width: 120px;" class="form-control" name="searchAddTime2"
                                   placeholder="结束时间">　　　
                        </div>-->
                            <button id="search" type="button" class="btn btn-primary" style="margin-left: 50px;">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索
                            </button>
                        </div>
                    </form>
                    <div id="maingrid" style="margin:0; padding:0"></div>

                    <div class="modal fade" id="TempModal" tabindex="-1" role="dialog" aria-labelledby="测温点温度"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:820px; height: 470px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="tempModalTitle">近24小时测温点温度</h4>
                                </div>
                                <div class="modal-body" style="padding: 10px;">
                                    <#--<div id="chartContainer" style="height: 370px; width: 820px; margin: 0px auto;"></div>-->
                                    <div id="mainChart" style="width: 800px;height:450px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "../shared/footer.ftl" />
    </div>
</div>
<#include "../shared/footerScript.ftl" />

<script src="/js/bootstrap-maxlength.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/bussiness/tempratureManage.js" type="text/javascript"></script>
<script src="/js/mEcharts.min.js"></script>
</body>
</html>
