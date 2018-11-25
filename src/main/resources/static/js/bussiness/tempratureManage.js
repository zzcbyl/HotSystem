//温度信息
var maingrid;
var stationJson = '';
var communityJson = '';
(function () {
    $(function () {
        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            {
                display: '测温点编号', name: 'positionID', width: 80, render: function (rowData) {
                    return "<a class='custom-a-style' href='javascript:void(0);' alt='点击查看温度曲线' onclick='showTemp(" + rowData.positionID + ")'>" + rowData.positionID + "</a>";
                }
            },
            {display: '设备编号', name: 'deviceSN', width: 100},
            //{display: '信号强度', name: 'level', width: 65},
            {display: '环境温度', name: 'temp1', width: 65},
            /*{display: '设备温度', name: 'temp2', width: 65},*/
            {
                display: '采集时间', name: 'addTime', width: 135, render: function (rowData) {
                    return rowData.addTime == null ? "" : new Date(rowData.addTime).format('Y-m-d H:i:s');
                }
            },
            {display: '所属分公司', name: 'companyName', width: 150},
            {display: '所属换热站', name: 'hesName', width: 150},
            {display: '小区', name: 'communityName', width: 150},
            {display: '楼号', name: 'buildingName', width: 60},
            {
                display: '安装位置', name: 'exactPosition', width: 200, render: function (rowData) {
                    return rowData.subNumber + "单元" + rowData.floorNumber + "层" + rowData.apartNumber +
                        (rowData.roomName == "" ? "" : "-" + rowData.roomName) +
                        (rowData.exactPosition == "" ? "" : "-" + rowData.exactPosition);
                }
            },
            {display: '住户姓名', name: 'customerName', width: 70},
            {display: '住户电话', name: 'phoneNumber', width: 90},
            {
                display: '查看', name: 'positionID', width: 120, render: function (rowData) {
                    return "<a class='custom-a-style' href='javascript:void(0);' alt='点击查看温度曲线' onclick='showTemp(" + rowData.positionID + ")'>查看温度曲线</a>";
                }
            }
            /*{
                display: '安装时间', name: 'deviceCreateTime', width: 90, render: function (rowData) {
                    return rowData.deviceCreateTime == null ? "" : new Date(rowData.deviceCreateTime).format('Y-m-d H:i:s');
                }
            },
            {
                display: '安装状态', name: 'deviceInstalled', width: 70, render: function (rowData) {
                    if (rowData.deviceInstalled == "1") {
                        return "已安装";
                    } else if (rowData.deviceInstalled == "2") {
                        return "安装中";
                    } else {
                        return "未安装";
                    }
                }
            },
            {
                display: '工作状态', name: 'deviceWorkState', width: 70, render: function (rowData) {
                    if (rowData.deviceWorkState == "2") {
                        return "故障";
                    } else if (rowData.deviceWorkState == "3") {
                        return "移除";
                    } else {
                        return "正常";
                    }
                }
            },
            {
                display: '状态', name: 'deviceStatus', width: 70, render: function (rowData) {
                    if (rowData.deviceStatus == "2") {
                        return "<span style='color: red'>停用</span>";
                    } else if (rowData.deviceStatus == "3") {
                        return "<span style='color: red'>已删除</span>";
                    } else {
                        return "<span style='color: green'>正常</span>";
                    }
                }
            }*/
        ];

        loadGrid();

        function loadGrid() {
            maingrid = $("#maingrid").ligerGrid({
                columns: col,
                dataAction: 'server',
                url: "/admin/temprature/getPaging",//从服务端加载数据
                parms: $('#search_form').serializeArray(),//这里是关键，传递搜索条件的参数  serializeArray是jquery自带的吧form转json传递的方法
                page: 1,
                checkbox: false,
                rownumbers: true,
                pageSize: 20,
                usePager: true,
                width: '99%',
                height: '98%',
                fixedCellHeight: false
                //toolbar: PostToolbarOptions
            });
        }

        //绑定分公司
        bindCompany();
        //绑定换热站
        $("#searchCompanyID").change(function () {
            bindStation();
        });
        //绑定小区
        $("#searchStationID").change(function () {
            bindCommunity();
        });
        //绑定楼号
        $("#searchCommunityID").change(function () {
            bindBuilding();
        });
        //绑定安装位置
        $("#searchBuildingID").change(function () {
            bindRoom();
        });

        var _stationService = app.createService('heatexchangestation');
        _stationService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            stationJson = data;
        });

        var _communityService = app.createService('community');
        _communityService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            communityJson = data;
        });

    });

})();

//绑定分公司
function bindCompany() {
    var _modelService = app.createService('company');
    _modelService.getAll({"searchParentID": -2}, {"page": 1, "pagesize": 20}, function (data) {
        var parentCompany = JSON.parse(data);
        if (parentCompany.Rows.length > 0) {
            $("#searchCompanyID").empty();
            $("#searchCompanyID").append("<option value=\"-1\">--请选择公司--</option>");
            for (var i = 0; i < parentCompany.Rows.length; i++) {
                $("#searchCompanyID").append("<option value='" + parentCompany.Rows[i].id + "'>" + parentCompany.Rows[i].name + "</option>");
            }
        }
    });
}

//绑定换热站
function bindStation() {
    $("#searchStationID").empty();
    $("#searchStationID").append("<option value=\"-1\">--请选择换热站--</option>");

    var companyId = $("#searchCompanyID").val();
    if (parseInt(companyId) > 0 && stationJson != '') {
        var parentStation = JSON.parse(stationJson);
        if (parentStation.Rows.length > 0) {
            for (var i = 0; i < parentStation.Rows.length; i++) {
                if (parentStation.Rows[i].companyID == companyId && parentStation.Rows[i].status == 1)
                    $("#searchStationID").append("<option value='" + parentStation.Rows[i].id + "'>" + parentStation.Rows[i].name + "</option>");
            }
        }
    }
}

//绑定小区
function bindCommunity() {
    $("#searchCommunityID").empty();
    $("#searchCommunityID").append("<option value=\"-1\">--请选择小区--</option>");

    var stationId = $("#searchStationID").val();
    if (parseInt(stationId) > 0 && communityJson != '') {
        var parentCommunity = JSON.parse(communityJson);
        if (parentCommunity.Rows.length > 0) {
            for (var i = 0; i < parentCommunity.Rows.length; i++) {
                if (parentCommunity.Rows[i].stationID == stationId && parentCommunity.Rows[i].status == 1)
                    $("#searchCommunityID").append("<option value='" + parentCommunity.Rows[i].id + "'>" + parentCommunity.Rows[i].name + "</option>");
            }
        }
    }
}

//绑定楼号
function bindBuilding() {
    var _modelService = app.createService('building');
    var communityID = $("#searchCommunityID").val();
    if (parseInt(communityID) > 0) {
        _modelService.getAll({"searchCommunityID": communityID}, {"page": 1, "pagesize": 99999}, function (data) {
            var parentBuilding = JSON.parse(data);
            if (parentBuilding.Rows.length > 0) {
                $("#searchBuildingID").empty();
                $("#searchBuildingID").append("<option value=\"-1\">--请选择楼号--</option>");
                for (var i = 0; i < parentBuilding.Rows.length; i++) {
                    $("#searchBuildingID").append("<option value='" + parentBuilding.Rows[i].id + "'>" + parentBuilding.Rows[i].name + "</option>");
                }
            }
        });
    }
}

//绑定安装位置
function bindRoom() {
    var _modelService = app.createService('position');
    var buildingID = $("#searchBuildingID").val();
    if (parseInt(buildingID) > 0) {
        _modelService.getAll({"searchBuildingID": buildingID}, {"page": 1, "pagesize": 9999}, function (data) {
            var parentRoom = JSON.parse(data);
            if (parentRoom.Rows.length > 0) {
                $("#searchRoomID").empty();
                $("#searchRoomID").append("<option value=\"-1\">--请选择房间位置--</option>");
                for (var i = 0; i < parentRoom.Rows.length; i++) {
                    $("#searchRoomID").append("<option value='" + parentRoom.Rows[i].id + "'>" + parentRoom.Rows[i].subNumber + "-" +
                        parentRoom.Rows[i].floorNumber + "-" + parentRoom.Rows[i].apartNumber + "-" + parentRoom.Rows[i].roomName + "</option>");
                }
            }
        });
    }
}


function showTemp(positonID) {
    var xData = [];// ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30', '03:00', '03:30', '04:00', '04:30', '05:00', '05:30', '06:00', '06:30'];
    var tempData = [];// [11, 11, 15, 13, 12, 13, 10, 26, 33, 31, 28, 18, 21, 30];
    var myChart = echarts.init(document.getElementById('mainChart'));
    $.ajax({
        url: '/admin/temprature/getList',
        data: {deviceID: -1, positionID: positonID, page: 1, pagesize: 50, sortname: "AddTime", sortorder: "asc"},
        beforeSend: function (request) {
            request.setRequestHeader("authorization", getToken());
        },
        method: "Post",
        success: function (data) {
            var tData = JSON.parse(data);
            if (tData != null && tData.Rows != null & tData.Rows.length > 0) {
                for (var i = 0; i < tData.Rows.length; i++) {
                    xData[i] = tData.Rows[i].addTime == null ? "" : new Date(tData.Rows[i].addTime).format('H:i');
                    tempData[i] = tData.Rows[i].temp1;
                }
                var option = {
                    title: {
                        text: '近24小时室内温度',
                        subtext: '',
                        x: 'center',
                        y: 'top',
                        textAlign: 'center'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        orient: 'horizontal',
                        x: 'center',
                        y: 'bottom',
                        data: ['室内温度']
                    },
                    grid: {
                        left: '5%',
                        right: '5%',
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: xData
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} °C'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '室内温度',
                            type: 'line',
                            data: tempData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            }
                        }
                    ]
                };
                myChart.setOption(option);

                if (tData.Rows.length > 0) {
                    var dWidth = tData.Rows.length * 30;
                    if (dWidth > 800) {
                        $('#TempModal .modal-dialog').eq(0).css("width", dWidth + 20 + "px");
                        $('#mainChart').css("width", dWidth + "px")
                        myChart.resize();
                    } else {
                        $('#TempModal .modal-dialog').eq(0).css("width", "820px");
                        $('#mainChart').css("width", "800px")
                        myChart.resize();
                    }
                }
                $("#tempModalTitle").html("测温点编号：" + positonID);
                $('#TempModal').modal("show");
            } else {
                alert("近24小时没有温度数据");
            }
        }
    });
}