//控制命令逻辑
var maingrid;
var deviceJSON = '';
var communityJSON = '';
var stationJSON = '';
var commandJSON = '';
(function () {
    $(function () {
        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';

        var _modelService = app.createService('controllist');
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');

        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            {display: '编号', name: 'id', width: 80},
            {
                display: '范围类型', name: 'rangeType', width: 150, render: function (rowData) {
                    if (rowData.rangeType == "1") {
                        return "指定设备";
                    } else if (rowData.rangeType == "2") {
                        return "小区";
                    } else if (rowData.rangeType == "3") {
                        return "换热站";
                    } else {
                        return "所有设备";
                    }
                }
            },
            {
                display: '范围对象', name: 'rangeObjectID', width: 250, render: function (rowData) {
                    if (rowData.rangeType == 1) {
                        return rowData.deviceSN;
                    } else if (rowData.rangeType == 2) {
                        return rowData.communityName;
                    } else if (rowData.rangeType == 3) {
                        return rowData.stationName;
                    } else {
                        return "所有设备";
                    }
                }
            },
            {display: '说明', name: 'name', width: 200},
            /*{display: '控制代码', name: 'remoteCode', width: 200},*/
            {display: '参数', name: 'note', width: 200},
            {
                display: '执行状态', name: 'executeStatus', width: 80, render: function (rowData) {
                    if (rowData.executeStatus == "0") {
                        return "待发送";
                    }
                    else if (rowData.executeStatus == "1") {
                        return "已发送";
                    } else {
                        return "已执行";
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
                display: '操作', width: 100, render: function (rowData, index) {
                    return "<a class='custom-a-style' style='font-weight: normal' href='javascript:void(0);' onclick='showRecord(" + rowData.id + ");'> 查看执行记录 </a>";
                }
            }
        ];
        var barcol = [
            {text: "添加", click: PostToolbarBtnItemClick, icon: 'add'},
            /*{text: "修改", click: PostToolbarBtnItemClick, icon: 'edit'},*/
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
                url: "/admin/controllist/getPaging",//从服务端加载数据
                parms: $('#search_form').serializeArray(),//这里是关键，传递搜索条件的参数  serializeArray是jquery自带的吧form转json传递的方法
                page: 1,
                checkbox: false,
                rownumbers: true,
                pageSize: 20,
                usePager: true,
                width: '99%',
                height: '98%',
                toolbar: PostToolbarOptions
            });
        }

        function PostToolbarBtnItemClick(item) {
            switch (item.text) {
                case "添加":
                    _$modal.modal("show");
                    _$modal.find("#myModalLabel").html("新建控制命令");
                    _$modal.find("input[name='id']").val('0');
                    _$modal.find("select[name='rangeType']").val('1');
                    bindRangeObject();
                    _$modal.find("select[name='remoteCode']").val('-1');
                    _$modal.find("input[name='note']").val('');
                    _$modal.find("select[name='status']").val('1');
                    break;
                case "修改":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }
                    _$modal.find("#myModalLabel").html("编辑控制命令");
                    _$modal.find("input[name='id']").val(selected[0].id);
                    _$modal.find("select[name='rangeType']").val(selected[0].rangeType);
                    bindRangeObject();
                    _$modal.find("select[name='rangeObjectID']").val(selected[0].rangeObjectID);
                    _$modal.find("select[name='remoteCode']").val(selected[0].remoteCode);
                    _$modal.find("input[name='note']").val(selected[0].note);
                    _$modal.find("select[name='status']").val(selected[0].status);
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

        //表单提交
        _$form.find('.submit').click(function (e) {
            e.preventDefault();
            _$form.data('bootstrapValidator').validate();
            if (!_$form.data('bootstrapValidator').isValid()) {
                return;
            }
            var model = _$form.serializeFormToObject();
            if (model.id == 0) {
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

        var _deviceService = app.createService("device");
        _deviceService.getAll({}, {"page": 1, "pagesize": 999}, function (data) {
            deviceJSON = JSON.parse(data);
        });
        var _communityService = app.createService("community");
        _communityService.getAll({}, {"page": 1, "pagesize": 999}, function (data) {
            communityJSON = JSON.parse(data);
        });
        var _stationService = app.createService("heatexchangestation");
        _stationService.getAll({}, {"page": 1, "pagesize": 999}, function (data) {
            stationJSON = JSON.parse(data);
        });


        //绑定命令代码
        bindCommand();
        bindNote();

        $('#rangeType').change(function () {
            bindRangeObject();
        });
        $('#remoteCode').change(function () {
            bindNote();
        });

        function bindCommand() {
            var _commandService = app.createService('commandlist');
            _commandService.getAll({}, {"page": 1, "pagesize": 999}, function (data) {
                commandJSON = JSON.parse(data);
                $("#searchRemoteCode").empty();
                $("#remoteCode").empty();
                if (commandJSON.Rows.length > 0) {
                    $("#searchRemoteCode").append("<option value=\"-1\">--请选择命令代码--</option>");
                    $("#remoteCode").append("<option value=\"-1\">--请选择命令代码--</option>");
                    for (var i = 0; i < commandJSON.Rows.length; i++) {
                        $("#searchRemoteCode").append("<option value='" + commandJSON.Rows[i].code + "'>" + commandJSON.Rows[i].name + "</option>");
                        $("#remoteCode").append("<option value='" + commandJSON.Rows[i].code + "'>" + commandJSON.Rows[i].name + "</option>");
                    }
                }
            });
        }
    });
})();

function bindRangeObject() {
    $("#rangeObjectID").empty();
    if ($('#rangeType').val() == "1") {
        if (deviceJSON.Rows.length > 0) {
            for (var i = 0; i < deviceJSON.Rows.length; i++) {
                $("#rangeObjectID").append("<option value='" + deviceJSON.Rows[i].id + "'>" + deviceJSON.Rows[i].deviceSN + "</option>");
            }
        }
    } else if ($('#rangeType').val() == "2") {
        if (communityJSON.Rows.length > 0) {
            for (var i = 0; i < communityJSON.Rows.length; i++) {
                $("#rangeObjectID").append("<option value='" + communityJSON.Rows[i].id + "'>" + communityJSON.Rows[i].name + "</option>");
            }
        }
    } else if ($('#rangeType').val() == "3") {
        if (stationJSON.Rows.length > 0) {
            for (var i = 0; i < stationJSON.Rows.length; i++) {
                $("#rangeObjectID").append("<option value='" + stationJSON.Rows[i].id + "'>" + stationJSON.Rows[i].name + "</option>");
            }
        }
    } else {
        $("#rangeObjectID").append("<option value='0'>所有设备</option>");
    }
}


function bindNote() {
    $('#name').val('');
    $('#deviceColumn').val('');
    if (commandJSON && commandJSON.Rows.length > 0) {
        for (var i = 0; i < commandJSON.Rows.length; i++) {
            if ($('#remoteCode').val() == commandJSON.Rows[i].code) {
                $('#name').val(commandJSON.Rows[i].name);
                $('#deviceColumn').val(commandJSON.Rows[i].deviceColumn);
                //$('#note').val(commandJSON.Rows[i].note);
            }
        }
    }
}

function showRecord(cid) {
    $("#recordGrid").ligerGrid({
        columns: [
            {display: '设备ID', name: 'deviceID', width: 80},
            {display: '设备编号', name: 'deviceSN', width: 80},
            /*{display: '执行命令', name: 'remoteCode', width: 120},*/
            {display: '参数', name: 'note', width: 100},
            {
                display: '发送时间', name: 'sendTime', width: 130, render: function (rowData) {
                    return rowData.sendTime == null ? "" : new Date(rowData.sendTime).format('Y-m-d H:i:s');
                }
            },
            {display: '返回结果', name: 'returnStatus', width: 200},
            {display: '执行状态', name: 'executeStatus', width: 90, render: function (rowData) {
                    if (rowData.executeStatus == "0") {
                        return "待发送";
                    }
                    else if (rowData.executeStatus == "1") {
                        return "已发送";
                    } else {
                        return "已执行";
                    }
                }},
        ],
        dataAction: 'server',
        url: "/admin/controlrecord/getPaging",//从服务端加载数据
        parms: {controlID: cid},
        page: 1,
        checkbox: false,
        rownumbers: true,
        pageSize: 20,
        usePager: true,
        width: '98%',
        height: '80%'
    });

    $("#recordModalTitle").html("设备执行列表");
    $('#RecordModal').modal("show");
}