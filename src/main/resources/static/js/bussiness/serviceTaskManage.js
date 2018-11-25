//任务逻辑

var serviceTask={
    userId:0,
    userName:''
};
(function () {
    $(function () {
        if(app.queryString('userId')) {
            serviceTask.userId=app.queryString('userId');
            serviceTask.userName=app.queryString("n");
            $("#aUserName").text(serviceTask.userName);
        }

        var _$ListTable = $('#ListTable');
        var _modelService = app.createService('serviceTask');
        
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');
        $('input[name="startTime"]').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss',
        });
        $('input[name="endTime"]').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss'
        });
        $("#btnAddNew").on("click", function () {

            _$modal.modal("show");
            
            _$modal.find("input[name='id']").val('0');

            _$modal.find("input[name='userID']").val('0');

            _$modal.find("input[name='customerMobile']").val('');

            _$modal.find("input[name='startTime']").val(Date());

            _$modal.find("input[name='endTime']").val(Date());

            _$modal.find("input[name='frequency']").val('0');

        });

        _$form.validate();

        _$form.find('.submit').click(function (e) {
            e.preventDefault();
            if (!_$form.valid()) {
                return;
            }
            var model = _$form.serializeFormToObject();
            
            
            if (!model.id) {
                _modelService.create(model,function (data) {
                    _$modal.modal('hide');
                    getModelList();
                })
            }
            else 
            {
                _modelService.update(
                model,function (data) {
                        _$modal.modal('hide');
                        getModelList();
                    }
                );
            }
        });
       
        _$modal.on('shown.bs.modal', function () {
            _$modal.find('input:not([type=hidden]):first').focus();
        });
    
        _$ListTable.jtable({
            pageList: "normal",
            title:'',
            paging: true,
            //selecting: true,
            //selectingCheckboxes :true,
            pageSizes: [10,20],
            pageSize:10,
            sorting: true,
            defaultSorting:"id Desc",
            //  multiSorting: true,  
            actions: {
                listAction:'/admin/serviceTask/getPaging'
            },

            fields: {
                //此处开始循环数据
                 
                
                id: {
                    title: '自增ID',
                    width: '5%',
                    key:true,
                    display:function(data){
                        return data.record.id;
                    }

                },

                userID: {
                    title: '任务发起方用户ID',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.userID;
                    }

                },

                CustomerMobile: {
                    title: '客户手机',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.customerMobile;
                    }

                },

                startTime: {
                    title: '开始时间',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return moment(data.record.startTime).format('YYYY年MM月DD日, h:mm:ss a');
                    }

                },

                endTime: {
                    title: '结束时间',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return moment(data.record.endTime).format('YYYY年MM月DD日, h:mm:ss a');
                    }

                },

                frequency: {
                    title: '频次（次数/分钟）',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.frequency;
                    }

                },

                state: {
                    title: '状态:0未处理，1：已完成',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.state;
                    }

                },

                createTime: {
                    title: '创建时间',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return moment(data.record.createTime).format('YYYY年MM月DD日, h:mm:ss a');
                    }

                }
            }
        });
        
         function EditModel(model)
        {
            _$modal.off('show.bs.modal').on('show.bs.modal', function (){
                
            _$modal.find("input[name='id']").val(model.id);

            _$modal.find("input[name='userID']").val(model.userID);

            _$modal.find("input[name='customerMobile']").val(model.customerMobile);

            _$modal.find("input[name='startTime']").val(model.startTime);

            _$modal.find("input[name='endTime']").val(model.endTime);

            _$modal.find("input[name='frequency']").val(model.frequency);

            });
            _$modal.modal("show");
        }


        //刷新表格信息
        $("#ButtonReload").click(function () {
            getModelList();
        });

        //模糊查询功能
        function getModelList(reload) {
            
            if (reload) {
                _$ListTable.jtable('reload');
            } else {
                _$ListTable.jtable('load', {
                    userId: serviceTask.userId,
                    //type:$("[name='selType']").val()
                });
            }
        }

        //搜索
        $('#btnSearch').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            getModelList();
        });
        getModelList();
    });
})();
