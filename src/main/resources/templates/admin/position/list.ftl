<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测温点管理</title>
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
                        Home</a> <a class="current" href="#">测温点管理</a></div>
                </div>
                <hr/>
                <div class="container-fluid">
                    <form id="search_form" class="form-inline" style="margin-bottom: 10px;">
                        <div class="form-group">
                            <label>查询：</label>
                            <input type="text" style="width: 100px;" class="form-control" name="searchPositionID" placeholder="测温点编号">　
                        </div>
                        <div class="form-group">
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
                            <select id="searchHeatExchangerUnitID" name="searchHeatExchangerUnitID" class="form-control">
                                <option value="-1">--请选择换热机组--</option>
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
                            <input type="text" style="width: 100px;" class="form-control" name="searchCustomerName" placeholder="住户姓名">　
                        </div>
                        <button id="search" type="button" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索
                        </button>
                    </form>
                    <div id="maingrid" style="margin:0; padding:0"></div>

                    <!--html代码区域-->
                    <div class="modal fade" id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="新建/编辑测温点"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:600px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">安装位置信息</h4>
                                </div>
                                <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                    <div class="modal-body">
                                        <input type="hidden" name="id" value="0"/>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>住户姓名<span class="required">  </span></label>
                                            <input type="hidden" name="customerID" value="0"/>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" id="customerName" name="customerName" value="" maxlength="20"
                                                   placeholder="请输入住户姓名"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所属公司<span class="required">  </span></label>
                                            <select id="companyID" name="companyID" class="form-control">
                                                <option value="-1">--请选择公司--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所属换热站<span class="required">  </span></label>
                                            <select id="stationID" name="stationID" class="form-control">
                                                <option value="-1">--请选择换热站--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所在换热机组<span class="required">  </span></label>
                                            <select id="heatExchangerUnitID" name="heatExchangerUnitID" class="form-control">
                                                <option value="-1">--请选择换热机组--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所在小区<span class="required">  </span></label>
                                            <select id="communityID" name="communityID" class="form-control">
                                                <option value="-1">--请选择小区--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼号<span class="required">  </span></label>
                                            <select id="buildingID" name="buildingID" class="form-control">
                                                <option value="-1">--请选择楼号--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>单元号<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="number" name="subNumber" value="" required maxlength="10"
                                                   placeholder="请输入单引号"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼层<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="number" name="floorNumber" value="" required maxlength="10"
                                                   placeholder="请收入楼层"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>门牌号<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="number" name="apartNumber" value="" required maxlength="10"
                                                   placeholder="请输入门牌号"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>房间名称<span class="required">  </span></label>
                                            <select id="roomName" name="roomName" class="form-control">
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>安装位置<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="exactPosition" value="" maxlength="10"
                                                   placeholder="请输入安装位置"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>房间/房屋面积<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="roomArea" value="" maxlength="10"
                                                   placeholder="请输入房间面积"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>维度<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="lati" value="" required maxlength="10"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>经度<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="longi" value="" required maxlength="10"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>高度<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="height" value="" required maxlength="10"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>与换热站的距离<span class="required">  </span></label>
                                            <select class="form-control" name="distance">
                                                <option value="">未知</option>
                                                <option value="近">近</option>
                                                <option value="中">中</option>
                                                <option value="远">远</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>住户位置</label>
                                            <select class="form-control" name="sideRoom">
                                                <option value="0">顶层</option>
                                                <option value="1">底层</option>
                                                <option value="2">中间位置</option>
                                                <option value="3">边角</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>住户编号<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="houseHoldID" value="" maxlength="22"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>热力入口编号<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="heatingEnteryID" value="" maxlength="25"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>采暖方式<span class="required">  </span></label>
                                            <select class="form-control" name="heatingType">
                                                <option value="0">未知</option>
                                                <option value="1">散热器</option>
                                                <option value="2">地暖</option>
                                            </select>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>状态<span class="required">  </span></label>
                                            <select class="form-control" name="status">
                                                <option value="1">正常</option>
                                                <option value="2">停用</option>
                                                <option value="3">删除</option>
                                            </select>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>备注<span class="required">  </span></label>
                                            <textarea name="remark" style="width:230px; display: inline; height: 50px" maxlength="200"
                                                      class="maxlength-handler form-control edited"></textarea>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label><span class="required">  </span></label>
                                            <span id="nameError" class="errorMessage"></span>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

                                        <button type="button" class="btn btn-primary blue submit"><i
                                                class="fa fa-save"></i> <span>保存</span></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="TempModal" tabindex="-1" role="dialog" aria-labelledby="测温点温度列表"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:800px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="tempModalTitle">近24小时测温点温度</h4>
                                </div>
                                <div class="modal-body" style="padding: 10px;">
                                    <div id="templGrid" style="margin:0; padding:0"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="TempChartModal" tabindex="-1" role="dialog" aria-labelledby="测温点温度"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:950px; height: 400px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="tempChartModalTitle">近24小时测温点温度</h4>
                                </div>
                                <div class="modal-body" style="padding: 10px;">
                                    <div id="chartContainer" style="height: 370px; width: 920px; margin: 0px auto;"></div>
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
<script src="/autosuggest/autosuggest.js"></script>
<script src="/js/bootstrap-maxlength.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/bussiness/positionManage.js" type="text/javascript"></script>
<script src="/js/mEcharts.js"></script>
</body>
</html>
