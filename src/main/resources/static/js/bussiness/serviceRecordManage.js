//任务执行记录逻辑
(function () {
    $(function () {
        var _$ListTable = $('#ListTable');
        var _modelService = app.createService('serviceRecord');
        
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');

        $("#btnAddNew").on("click", function () {

            _$modal.modal("show");
            
            _$modal.find("input[name='id']").val('0');

            _$modal.find("input[name='serviceTaskID']").val('0');

            _$modal.find("input[name='from']").val('');

            _$modal.find("input[name='to']").val('');

            _$modal.find("input[name='taskTime']").val(Date());

            _$modal.find("input[name='state']").val('0');

            _$modal.find("input[name='userID']").val('0');
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
                listAction:'/admin/serviceRecord/getPaging'
            },

            fields: {
                //此处开始循环数据
                 
                
                id: {
                    title: '',
                    width: '5%',
                    key:true,
                    display:function(data){
                        return data.record.id;
                    }

                },

                serviceTaskID: {
                    title: '任务ID',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.serviceTaskID;
                    }

                },

                from: {
                    title: '客服手机',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.from;
                    }

                },

                to: {
                    title: '客户手机',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.to;
                    }

                },

                taskTime: {
                    title: '执行时间',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return moment(data.record.taskTime).format('YYYY年MM月DD日, h:mm:ss a');
                    }

                },

                state: {
                    title: '状态',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.state==1?'完成':'异常';
                    }

                },

             /*   userID: {
                    title: '任务发起人ID',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.userID;
                    }

                }*/
            }
        });
        
         function EditModel(model)
        {
            _$modal.off('show.bs.modal').on('show.bs.modal', function (){
                
            _$modal.find("input[name='id']").val(model.id);

            _$modal.find("input[name='serviceTaskID']").val(model.serviceTaskID);

            _$modal.find("input[name='from']").val(model.from);

            _$modal.find("input[name='to']").val(model.to);

            _$modal.find("input[name='taskTime']").val(model.taskTime);

            _$modal.find("input[name='state']").val(model.state);

            _$modal.find("input[name='userID']").val(model.userID);
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
                    //userId: accountHistory.userId,
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
