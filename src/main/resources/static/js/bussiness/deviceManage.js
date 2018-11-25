//设备信息表逻辑
var maingrid;

(function () {
    $(function () {
        var _modelService = app.createService('device');

        //检索
        $("#search").click(function () {
            var reg = /^([0-9]+(\.\d+)?|0\.\d+)$/;
            if ($('#searchLevel1').val() != "") {
                if (!reg.test($('#searchLevel1').val())) {
                    alert("信号强度只能为正浮点数");
                }
            }
            if ($('#searchLevel2').val() != "") {
                if (!reg.test($('#searchLevel2').val())) {
                    alert("信号强度只能为正浮点数");
                }
            }

            loadGrid();
        });

        var col = [
            {display: '编号', name: 'id', width: 80},
            {display: '设备编号', name: 'deviceSN', width: 150},
            {display: '设备类型名称', name: 'typeName', width: 150},
            {display: '校正参数1', name: 'parameter1', width: 120},
            {display: '校正参数2', name: 'parameter1', width: 120},
            {display: '采集时间间隔(分钟)', name: 'interlave', width: 120},
            {display: '最新信号强度', name: 'level', width: 120},
            {display: '最新室内温度', name: 'temp1', width: 120},
            {display: '最新设备温度', name: 'temp2', width: 120},
            {
                display: '最新温度采集时间', name: 'collectTime', width: 150, render: function (rowData) {
                    return rowData.collectTime == null ? "无" : new Date(rowData.collectTime).format('Y-m-d H:i:s');
                }
            },
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

        loadGrid();

        function loadGrid() {
            maingrid = $("#maingrid").ligerGrid({
                columns: col,
                dataAction: 'server',
                url: "/admin/device/getPaging",//从服务端加载数据
                parms: $('#search_form').serializeArray(),//这里是关键，传递搜索条件的参数  serializeArray是jquery自带的吧form转json传递的方法
                page: 1,
                checkbox: false,
                rownumbers: true,
                pageSize: 20,
                usePager: true,
                width: '99%',
                height: '98%'
            });
        }
    });
})();






