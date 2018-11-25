//房间信息逻辑
var maingrid;
var _$modal;
var _$form;
var buildingJson = '';

(function () {
    $(function () {
        var _modelService = app.createService('room');
        _$modal = $('#CreateModal');
        _$form = _$modal.find('form');

        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';
        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            {display: '编号', name: 'id', width: 80},
            {display: '小区名称', name: 'name', width: 200},
            {display: '所属换热站', name: 'stationName', width: 250},
            {
                display: '状态', name: 'status', width: 100, render: function (rowData) {
                    if (rowData.status == "2") {
                        return "<span style='color: red'>停用</span>";
                    } else if (rowData.status == "3") {
                        return "<span style='color: red'>已删除</span>";
                    } else {
                        return "<span style='color: green'>正常</span>";
                    }
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
                url: "/admin/room/getPaging",//从服务端加载数据
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
                    _$modal.find("input[name='id']").val('0');
                    _$modal.find("input[name='customerID']").val('0');
                    _$modal.find("input[name='customerName']").val('');
                    _$modal.find("input[name='lati']").val('0');
                    _$modal.find("input[name='longi']").val('0');
                    _$modal.find("input[name='hight']").val('');
                    _$modal.find("select[name='communityID']").val('-1');
                    _$modal.find("select[name='buildingID']").val('-1');
                    _$modal.find("input[name='subNumber']").val('0');
                    _$modal.find("input[name='floorNumber']").val('0');
                    _$modal.find("input[name='apartNumber']").val('0');
                    _$modal.find("input[name='roomName']").val('');
                    _$modal.find("input[name='sideRoom']").attr("checked", false);
                    _$modal.find("input[name='roomArea']").val('');
                    _$modal.find("input[name='status']").val('1');
                    break;
                case "修改":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }
                    _$modal.find("input[name='id']").val(selected[0].id);
                    _$modal.find("input[name='customerID']").val(selected[0].customerID);
                    _$modal.find("input[name='customerName']").val(selected[0].customerName);
                    _$modal.find("input[name='lati']").val(selected[0].lati);
                    _$modal.find("input[name='longi']").val(selected[0].longi);
                    _$modal.find("input[name='hight']").val(selected[0].hight);
                    _$modal.find("select[name='communityID']").val(selected[0].communityID);
                    bindBuilding("communityID", "buildingID");
                    _$modal.find("select[name='buildingID']").val(selected[0].buildingID);
                    _$modal.find("input[name='subNumber']").val(selected[0].subNumber);
                    _$modal.find("input[name='floorNumber']").val(selected[0].floorNumber);
                    _$modal.find("input[name='apartNumber']").val(selected[0].apartNumber);
                    _$modal.find("input[name='roomName']").val(selected[0].roomName);
                    _$modal.find("input[name='sideRoom']").val(selected[0].sideRoom);
                    _$modal.find("input[name='roomArea']").val(selected[0].roomArea);
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
                    name: {
                        message: '小区名称验证失败',
                        validators: {
                            notEmpty: {
                                message: '小区名称不能为空'
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

        //绑定小区和楼房
        bindCommunity();
        $("#searchCommunityID").change(function () {
            bindBuilding("searchCommunityID", "searchBuildingID");
        });
        $("#communityID").change(function () {
            bindBuilding("communityID", "buildingID");
        });

        var _buildingService = app.createService('building');
        _buildingService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            buildingJson = data;
        });
    });
})();


function bindCommunity() {
    //绑定小区
    var _communityService = app.createService('community');
    _communityService.getAll({"searchParentID": -2}, {"page": 1, "pagesize": 20}, function (data) {
        var parentCommunity = JSON.parse(data);
        if (parentCommunity.Rows.length > 0) {
            $("#searchCommunityID").empty();
            $("#communityID").empty();
            $("#searchCommunityID").append("<option value=\"-1\">--请选择小区--</option>");
            $("#communityID").append("<option value=\"-1\">--请选择小区--</option>");
            for (var i = 0; i < parentCommunity.Rows.length; i++) {
                if (parentCommunity.Rows[i].status == 1) {
                    $("#searchCommunityID").append("<option value='" + parentCommunity.Rows[i].id + "'>" + parentCommunity.Rows[i].name + "</option>");
                    $("#communityID").append("<option value='" + parentCommunity.Rows[i].id + "'>" + parentCommunity.Rows[i].name + "</option>");
                }
            }
        }
    });
}

function bindBuilding(communityIDStr, buildingIDStr) {
    //绑定楼房
    $("#" + buildingIDStr).empty();
    $("#" + buildingIDStr).append("<option value=\"-1\">--请选择楼号--</option>");

    var communityId = $("#" + communityIDStr).val();
    if (parseInt(communityId) > 0 && buildingJson != '') {
        var parentBuilding = JSON.parse(buildingJson);
        if (parentBuilding.Rows.length > 0) {
            for (var i = 0; i < parentBuilding.Rows.length; i++) {
                if (parentBuilding.Rows[i].communityID == communityId && parentBuilding.Rows[i].status == 1)
                    $("#" + buildingIDStr).append("<option value='" + parentBuilding.Rows[i].id + "'>" + parentBuilding.Rows[i].name + "</option>");
            }
        }
    }
}

