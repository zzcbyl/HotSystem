//设备安装管理逻辑
var maingrid;
var stationJson = '';
var communityJson = '';
var buildingJson = '';
(function () {
    $(function () {
        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';

        var _modelService = app.createService('deviceinstall');
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');

        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            /*{display: '编号', name: 'id', width: 50},*/
            {display: '测温点编号', name: 'positionID', width: 80},
            {
                display: '设备编号', name: 'deviceSN', width: 80, render: function (rowData) {
                    return "<a class='custom-a-style' href='javascript:void(0);' alt='点击查看信号强度' onclick='showLevel(" + rowData.deviceID + ",\"" + rowData.deviceSN + "\")'>" + rowData.deviceSN + "</a>";
                }
            },
            /*{display: '设备的手机卡号', name: 'equipmentPhone', width: 90},
            {display: '校正参数1', name: 'parameter1', width: 90},
            {display: '校正参数2', name: 'parameter1', width: 90},
            {display: '采集间隔(分钟)', name: 'interlave', width: 100},*/
            {display: '最新信号强度', name: 'level', width: 100},
            {display: '所属分公司', name: 'companyName', width: 150},
            {display: '所属换热站', name: 'hesName', width: 150},
            {
                display: '小区/楼号', name: 'communityName', width: 120, render: function (rowData) {
                    return rowData.communityName + "<br />" + rowData.buildingName;
                }
            },
            {
                display: '安装位置', name: 'exactPosition', width: 150, render: function (rowData) {
                    return rowData.subNumber + "单元" + rowData.floorNumber + "层" + rowData.apartNumber + "<br />" +
                        (rowData.roomName == "" ? "" : "" + rowData.roomName) +
                        (rowData.exactPosition == "" ? "" : "-" + rowData.exactPosition);
                }
            },
            {
                display: '住户信息', name: 'customerName', width: 100, render: function (rowData) {
                    return rowData.customerName + "<br/>" + rowData.phoneNumber;
                }
            },
            {
                display: '安装时间', name: 'createTime', width: 90, render: function (rowData) {
                    return rowData.createTime == null ? "" : new Date(rowData.createTime).format('Y-m-d H:i:s');
                }
            },
            {
                display: '安装状态', name: 'installed', width: 80, render: function (rowData) {
                    if (rowData.installed == "1") {
                        return "已安装";
                    } else if (rowData.installed == "2") {
                        return "安装中";
                    } else {
                        return "未安装";
                    }
                }
            },
            {
                display: '工作状态', name: 'workState', width: 80, render: function (rowData) {
                    if (rowData.workState == "2") {
                        return "故障";
                    } else if (rowData.workState == "3") {
                        return "移除";
                    } else {
                        return "正常";
                    }
                }
            },
            {
                display: '状态', name: 'status', width: 80, render: function (rowData) {
                    if (rowData.status == "2") {
                        return "<span style='color: red'>停用</span>";
                    } else if (rowData.status == "3") {
                        return "<span style='color: red'>已删除</span>";
                    } else {
                        return "<span style='color: green'>正常</span>";
                    }
                }
            },
            {
                display: '操作', width: 120, render: function (rowData, index) {
                    return "<a class='custom-a-style' style='font-weight: normal' href='javascript:void(0);' onclick='showDevice(" + rowData.deviceID + ");'> 查看设备信息 </a><br />" +
                        "<a class='custom-a-style' style='font-weight: normal' href='javascript:void(0);' onclick='showCommand(" + rowData.deviceID + ",\"" + rowData.deviceSN + "\");'> 发送控制命令 </a>";
                }
            }
        ];

        var barcol = [
            {text: "添加", click: PostToolbarBtnItemClick, icon: 'add'},
            {text: "修改", click: PostToolbarBtnItemClick, icon: 'edit'},
            {text: "删除", click: PostToolbarBtnItemClick, icon: 'delete'}
        ];
        PostToolbarOptions = {
            items: barcol,
        };

        loadGrid();

        function loadGrid() {
            maingrid = $("#maingrid").ligerGrid({
                columns: col,
                dataAction: 'server',
                url: "/admin/deviceinstall/getPaging",//从服务端加载数据
                parms: $('#search_form').serializeArray(),//这里是关键，传递搜索条件的参数  serializeArray是jquery自带的吧form转json传递的方法
                page: 1,
                checkbox: false,
                rownumbers: true,
                pageSize: 20,
                usePager: true,
                width: '99%',
                height: '98%',
                fixedCellHeight: false,
                toolbar: PostToolbarOptions
            });
        }

        function PostToolbarBtnItemClick(item) {
            switch (item.text) {
                case "添加":
                    _$modal.modal("show");
                    _$modal.find("#myModalLabel").html("添加设备安装记录");
                    _$modal.find("input[name='id']").val('0');
                    _$modal.find("input[name='deviceSN']").val('');
                    _$modal.find("input[name='equipmentChipID']").val('');
                    _$modal.find("input[name='communicationCard']").val('');
                    _$modal.find("input[name='equipmentPhone']").val('');
                    _$modal.find("input[name='positionID']").val('');
                    _$modal.find("select[name='companyID']").val('-1');
                    bindStation("companyID", "stationID");
                    _$modal.find("select[name='stationID']").val('-1');
                    bindCommunity("stationID", "communityID");
                    _$modal.find("select[name='communityID']").val('-1');
                    bindBuilding("communityID", "buildingID");
                    bindRoom("buildingID", "roomID", function () {
                        _$modal.find("select[name='buildingID']").val('-1');
                    });
                    _$modal.find("select[name='roomID']").val('-1');
                    _$modal.find("select[name='installed']").val('1');
                    _$modal.find("select[name='status']").val('1');
                    _$modal.find("textarea[name='remark']").val('');
                    break;
                case "修改":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }
                    _$modal.find("#myModalLabel").html("编辑设备安装记录");
                    _$modal.find("input[name='id']").val(selected[0].id);
                    _$modal.find("input[name='deviceSN']").val(selected[0].deviceSN);
                    _$modal.find("input[name='equipmentChipID']").val(selected[0].equipmentChipID);
                    _$modal.find("input[name='communicationCard']").val(selected[0].communicationCard);
                    _$modal.find("input[name='equipmentPhone']").val(selected[0].equipmentPhone);
                    _$modal.find("input[name='positionID']").val(selected[0].positionID);
                    _$modal.find("select[name='companyID']").val(selected[0].companyID);
                    bindStation("companyID", "stationID");
                    //$(".selector").find("option[text='pxx']").attr("selected",true);
                    _$modal.find("select[name='stationID']").val(selected[0].hesID);
                    bindCommunity("stationID", "communityID");
                    _$modal.find("select[name='communityID']").val(selected[0].communityID);
                    bindBuilding("communityID", "buildingID");
                    _$modal.find("select[name='buildingID']").val(selected[0].buildingID);
                    bindRoom("buildingID", "roomID", function () {
                        _$modal.find("select[name='roomID']").val(selected[0].positionID);
                    });
                    _$modal.find("select[name='installed']").val(selected[0].installed);
                    _$modal.find("select[name='status']").val(selected[0].status);
                    _$modal.find("textarea[name='remark']").val(selected[0].remark);
                    _$modal.modal("show");
                    break;
                case "查看":
                    break;
                case "删除":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }

                    if (confirm('确定要删除该数据吗？')) {
                        _modelService.delete({
                                id: selected[0].id
                            },
                            function (data) {
                                maingrid.loadData();
                            },
                            function (error) {

                            });
                    }
                    break;
            }
        }

        //表单验证
        formValidator();

        function formValidator() {
            _$form.bootstrapValidator({
                message: 'This value is not valid',
                fields: {
                    deviceSN: {
                        validators: {
                            notEmpty: {
                                message: '设备编号不能为空'
                            }
                        }
                    },
                    positionID: {
                        trigger: "change",
                        validators: {
                            notEmpty: {
                                message: '测温点编号不能为空　'
                            }
                        }
                    }
                }
            });
        }

        //表单提交
        _$form.find('.submit').click(function (e) {
            e.preventDefault();
            _$form.data('bootstrapValidator').validate();
            if (!_$form.data('bootstrapValidator').isValid()) {
                return;
            }
            var model = _$form.serializeFormToObject();
            if (model.id == 0) {
                model.createrID = getLoginUser().id;
                _modelService.create(model, function (data) {
                    _$modal.modal('hide');
                    maingrid.loadData();
                })
            }
            else {
                _modelService.update(
                    model, function (data) {
                        _$modal.modal('hide');
                        maingrid.loadData();
                    }
                );
            }
        });

        _$modal.on('hidden.bs.modal', function () {
            _$form.data('bootstrapValidator').destroy();
            _$form.data('bootstrapValidator', null);
            formValidator();
        });

        //绑定控制代码
        bindCommand();
        $('#remoteCode').change(function () {
            bindNote();
        });
        //绑定分公司
        bindCompany();
        //绑定换热站
        $("#searchCompanyID").change(function () {
            bindStation("searchCompanyID", "searchStationID");
        });
        $("#companyID").change(function () {
            bindStation("companyID", "stationID");
        });
        //绑定小区
        $("#searchStationID").change(function () {
            bindCommunity("searchStationID", "searchCommunityID");
        });
        $("#stationID").change(function () {
            bindCommunity("stationID", "communityID");
        });
        //绑定楼号
        $("#searchCommunityID").change(function () {
            bindBuilding("searchCommunityID", "searchBuildingID");
        });
        $("#communityID").change(function () {
            bindBuilding("communityID", "buildingID");
        });
        //绑定安装位置
        $("#searchBuildingID").change(function () {
            bindRoom("searchBuildingID", "searchRoomID");
        });
        $("#buildingID").change(function () {
            bindRoom("buildingID", "roomID");
        });
        //回填测温点编号
        $("#roomID").change(function () {
            if ($(this).val() != "-1")
                _$modal.find("input[name='positionID']").val($(this).val()).change();
            else {
                _$modal.find("input[name='positionID']").val("").change();
            }
        });

        //根据测温点编号绑定测温点信息
        _$modal.find("input[name='positionID']").blur(function () {
            if ($.trim($(this).val()) != "") {
                $.ajax({
                    url: '/admin/position/get',
                    data: {id: $(this).val()},
                    method: "Get",
                    beforeSend: function (request) {
                        request.setRequestHeader("authorization", getToken());
                    },
                    success: function (data) {
                        var positionModel = data.data.result;

                        var myStationID = -1;
                        if (communityJson != '') {
                            var parentCommunity = JSON.parse(communityJson);
                            if (parentCommunity.Rows.length > 0) {
                                for (var i = 0; i < parentCommunity.Rows.length; i++) {
                                    if (parentCommunity.Rows[i].id == positionModel.communityID && parentCommunity.Rows[i].status == 1) {
                                        myStationID = parentCommunity.Rows[i].stationID;
                                    }
                                }
                            }
                        }
                        var myCompanyID = -1;
                        if (myStationID > 0 && stationJson != '') {
                            var parentStation = JSON.parse(stationJson);
                            if (parentStation.Rows.length > 0) {
                                for (var i = 0; i < parentStation.Rows.length; i++) {
                                    if (parentStation.Rows[i].id == myStationID && parentStation.Rows[i].status == 1) {
                                        myCompanyID = parentStation.Rows[i].companyID;
                                    }
                                }
                            }
                        }

                        _$modal.find("select[name='companyID']").val(myCompanyID);
                        bindStation("companyID", "stationID");
                        _$modal.find("select[name='stationID']").val(myStationID);
                        bindCommunity("stationID", "communityID");
                        _$modal.find("select[name='communityID']").val(positionModel.communityID);
                        bindBuilding("communityID", "buildingID");
                        _$modal.find("select[name='buildingID']").val(positionModel.buildingID);
                        bindRoom("buildingID", "roomID", function () {
                            _$modal.find("select[name='roomID']").val(positionModel.id);
                        });
                    },
                    error: function (error) {

                    }
                });
            }
        });

        var _stationService = app.createService('heatexchangestation');
        _stationService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            stationJson = data;
        });

        var _communityService = app.createService('community');
        _communityService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            communityJson = data;
        });

        var _buildingService = app.createService('building');
        _buildingService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            buildingJson = data;
        });
    });

})();

//绑定分公司
function bindCompany() {
    $("#searchCompanyID").empty();
    $("#companyID").empty();
    $("#searchCompanyID").append("<option value=\"-1\">--请选择公司--</option>");
    $("#companyID").append("<option value=\"-1\">--请选择公司--</option>");
    var _modelService = app.createService('company');
    _modelService.getAll({"searchParentID": -2}, {"page": 1, "pagesize": 20}, function (data) {
        var parentCompany = JSON.parse(data);
        if (parentCompany.Rows.length > 0) {
            for (var i = 0; i < parentCompany.Rows.length; i++) {
                if (parentCompany.Rows[i].status == 1) {
                    $("#searchCompanyID").append("<option value='" + parentCompany.Rows[i].id + "'>" + parentCompany.Rows[i].name + "</option>");
                    $("#companyID").append("<option value='" + parentCompany.Rows[i].id + "'>" + parentCompany.Rows[i].name + "</option>");
                }
            }
        }
    });
}

//绑定换热站
function bindStation(companyIDStr, stationIDStr) {
    //绑定换热站
    $("#" + stationIDStr).empty();
    $("#" + stationIDStr).append("<option value=\"-1\">--请选择换热站--</option>");

    var companyId = $("#" + companyIDStr).val();
    if (parseInt(companyId) > 0 && stationJson != '') {
        var parentStation = JSON.parse(stationJson);
        if (parentStation.Rows.length > 0) {
            for (var i = 0; i < parentStation.Rows.length; i++) {
                if (parentStation.Rows[i].companyID == companyId && parentStation.Rows[i].status == 1)
                    $("#" + stationIDStr).append("<option value='" + parentStation.Rows[i].id + "'>" + parentStation.Rows[i].name + "</option>");
            }
        }
    }
}

//绑定小区
function bindCommunity(stationIDStr, communityIDStr) {
    $("#" + communityIDStr).empty();
    $("#" + communityIDStr).append("<option value=\"-1\">--请选择小区--</option>");
    var stationId = $("#" + stationIDStr).val();
    if (parseInt(stationId) > 0 && communityJson != '') {
        var parentCommunity = JSON.parse(communityJson);
        if (parentCommunity.Rows.length > 0) {
            for (var i = 0; i < parentCommunity.Rows.length; i++) {
                if (parentCommunity.Rows[i].stationID == stationId && parentCommunity.Rows[i].status == 1) {
                    $("#" + communityIDStr).append("<option value='" + parentCommunity.Rows[i].id + "'>" + parentCommunity.Rows[i].name + "</option>");
                }
            }
        }
    }
}

//绑定楼号
function bindBuilding(communityIDStr, buildingIDStr) {
    $("#" + buildingIDStr).empty();
    $("#" + buildingIDStr).append("<option value=\"-1\">--请选择楼号--</option>");
    var communityID = $("#" + communityIDStr).val();
    if (parseInt(communityID) > 0 && buildingJson != '') {
        var parentBuilding = JSON.parse(buildingJson);
        if (parentBuilding.Rows.length > 0) {
            for (var i = 0; i < parentBuilding.Rows.length; i++) {
                if (parentBuilding.Rows[i].communityID == communityID && parentBuilding.Rows[i].status == 1) {
                    $("#" + buildingIDStr).append("<option value='" + parentBuilding.Rows[i].id + "'>" + parentBuilding.Rows[i].name + "</option>");
                }
            }
        }
    }
}

//绑定搜索安装位置
function bindRoom(buildingIDStr, roomIDStr, successFun) {
    $("#" + roomIDStr).empty();
    $("#" + roomIDStr).append("<option value=\"-1\">--请选择安装位置--</option>");
    var _positionService = app.createService('position');
    var buildingID = $("#" + buildingIDStr).val();
    if (parseInt(buildingID) > 0) {
        _positionService.getAll({"searchBuildingID": buildingID}, {"page": 1, "pagesize": 9999}, function (data) {
            var parentRoom = JSON.parse(data);
            if (parentRoom.Rows.length > 0) {
                for (var i = 0; i < parentRoom.Rows.length; i++) {
                    $("#" + roomIDStr).append("<option value='" + parentRoom.Rows[i].id + "'>" + parentRoom.Rows[i].subNumber + "单元" +
                        parentRoom.Rows[i].floorNumber + "层" + parentRoom.Rows[i].apartNumber +
                        (parentRoom.Rows[i].roomName == "" ? "" : "-" + parentRoom.Rows[i].roomName) +
                        (parentRoom.Rows[i].exactPosition == "" ? "" : "-" + parentRoom.Rows[i].exactPosition) + "</option>");
                }
            }

            successFun && successFun(data);
        });
    }
}


function showLevel(deviceID, deviceSN) {
    $("#levelGrid").ligerGrid({
        columns: [
            {display: '信号强度', name: 'level', width: 120},
            {display: '室内温度', name: 'temp1', width: 120},
            {display: '设备温度', name: 'temp2', width: 120},
            {
                display: '采集时间', name: 'addTime', width: 220, render: function (rowData) {
                    return rowData.addTime == null ? "" : new Date(rowData.addTime).format('Y-m-d H:i:s');
                }
            },
        ],
        dataAction: 'server',
        url: "/admin/temprature/getList",//从服务端加载数据
        parms: {deviceID: deviceID},
        page: 1,
        checkbox: false,
        rownumbers: true,
        pageSize: 20,
        usePager: true,
        width: '98%',
        height: '80%'
    });

    $("#levelModalTitle").html("设备近24小时内数据(设备编号：" + deviceSN + ")");
    $('#LevelModal').modal("show");
}

function showDevice(deviceID) {
    var _deviceService = app.createService('device');
    _deviceService.get(deviceID, function (data) {
        $('#deviceSN').html(data.data.result.deviceSN);
        $('#equipmentChipID').html(data.data.result.equipmentChipID);
        $('#communicationCard').html(data.data.result.communicationCard);
        $('#equipmentPhone').html(data.data.result.equipmentPhone);
        $('#deviceInitialTemp').html(data.data.result.deviceInitialTemp);
        $('#parameter1').html(data.data.result.parameter1);
        $('#parameter2').html(data.data.result.parameter2);
        $('#interlave').html(data.data.result.interlave);
        $('#firmwareVersion').html(data.data.result.firmwareVersion);
        $('#createTime').html(new Date(data.data.result.createTime).format('Y-m-d H:i:s'));
        $('#temp1').html(data.data.result.temp1);
        $('#temp2').html(data.data.result.temp2);
        $('#level').html(data.data.result.level);
        $('#collectTime').html(data.data.result.collectTime == null ? "" : new Date(data.data.result.collectTime).format('Y-m-d H:i:s'));
    });

    $("#deviceModalTitle").html("设备信息");
    $('#DeviceModal').modal("show");
}

//发送控制命令
function showCommand(deviceID, deviceSN) {
    $("#remoteCode").val(-1);
    $("#note").val('');
    $('#c_deviceSN').val(deviceSN);
    $('#rangeObjectID').val(deviceID);
    $('#CommandModal').modal("show");
    commandFormValidator();
}

var commandJSON = '';
var _$commandModal = $('#CommandModal');
var _$commandForm = _$commandModal.find('form');

_$commandModal.on('hidden.bs.modal', function () {
    _$commandForm.data('bootstrapValidator').destroy();
    _$commandForm.data('bootstrapValidator', null);
    commandFormValidator();
});

function sendCommand() {
    _$commandForm.data('bootstrapValidator').validate();
    if (!_$commandForm.data('bootstrapValidator').isValid()) {
        return;
    }
    var _commandModelService = app.createService('controllist');
    var commandModel = _$commandForm.serializeFormToObject();
    _commandModelService.create(commandModel, function (data) {
        _$commandModal.modal('hide');
    })
}

function commandFormValidator() {
    _$commandForm.bootstrapValidator({
        message: 'This value is not valid',
        fields: {
            rangeObjectID: {
                message: '范围对象编号验证失败',
                validators: {
                    notEmpty: {
                        message: '范围对象编号不能为空'
                    }
                }
            },
            remoteCode: {
                message: '控制代码验证失败',
                validators: {
                    notEmpty: {
                        message: '控制代码不能为空'
                    },
                    callback: {
                        message: '请选择控制代码',
                        callback: function (value, validator) {
                            if (value == -1) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                    }
                }
            },
            note: {
                message: '参数值验证失败',
                validators: {
                    notEmpty: {
                        message: '参数值不能为空'
                    }
                }
            }
        }
    });
}

function bindCommand() {
    var _commandService = app.createService('commandlist');
    _commandService.getAll({}, {"page": 1, "pagesize": 999}, function (data) {
        commandJSON = JSON.parse(data);
        $("#remoteCode").empty();
        if (commandJSON.Rows.length > 0) {
            $("#remoteCode").append("<option value=\"-1\">--请选择命令代码--</option>");
            for (var i = 0; i < commandJSON.Rows.length; i++) {
                $("#remoteCode").append("<option value='" + commandJSON.Rows[i].code + "'>" + commandJSON.Rows[i].name + "</option>");
            }
        }
    });
}

function bindNote() {
    $('#c_name').val('');
    $('#c_deviceColumn').val('');
    if (commandJSON && commandJSON.Rows.length > 0) {
        for (var i = 0; i < commandJSON.Rows.length; i++) {
            if ($('#remoteCode').val() == commandJSON.Rows[i].code) {
                $('#c_name').val(commandJSON.Rows[i].name);
                $('#c_deviceColumn').val(commandJSON.Rows[i].deviceColumn);
            }
        }
    }
}