//报警配置逻辑
var maingrid;

(function () {
    $(function () {
        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';

        var _modelService = app.createService('alarmconfig');
        var _$modal = $('#FormModel');
        var _$form = _$modal.find('form');

        _modelService.getAll({}, {"page": 1, "pagesize": 1}, function (data) {
            var config = JSON.parse(data);
            if (config.Rows.length > 0) {
                var model = config.Rows[0];
                _$modal.find("input[name='id']").val(model.id);
                _$modal.find("input[name='lowAlarm2']").val(model.lowAlarm2);
                _$modal.find("input[name='lowAlarm1']").val(model.lowAlarm1);
                _$modal.find("input[name='highAlarm1']").val(model.highAlarm1);
                _$modal.find("input[name='highAlarm2']").val(model.highAlarm2);
                _$modal.find("input[name='color_low2']").val(model.color_low2);
                _$modal.find("input[name='color_low1']").val(model.color_low1);
                _$modal.find("input[name='color_Normal']").val(model.color_Normal);
                _$modal.find("input[name='color_High1']").val(model.color_High1);
                _$modal.find("input[name='color_High2']").val(model.color_High2);
            }
        });

        _$modal.find("input[name='color_low1']").colpick({
            submit: false,
            onChange: function (color1, color2) {
                _$modal.find("input[name='color_low1']").val("#" + color2.toUpperCase());
            }
        });
        _$modal.find("input[name='color_low2']").colpick({
            submit: false,
            onChange: function (color1, color2) {
                _$modal.find("input[name='color_low2']").val("#" + color2.toUpperCase());
            }
        });
        _$modal.find("input[name='color_High1']").colpick({
            submit: false,
            onChange: function (color1, color2) {
                _$modal.find("input[name='color_High1']").val("#" + color2.toUpperCase());
            }
        });
        _$modal.find("input[name='color_High2']").colpick({
            submit: false,
            onChange: function (color1, color2) {
                _$modal.find("input[name='color_High2']").val("#" + color2.toUpperCase());
            }
        });
        _$modal.find("input[name='color_Normal']").colpick({
            submit: false,
            onChange: function (color1, color2) {
                _$modal.find("input[name='color_Normal']").val("#" + color2.toUpperCase());
                _$form.data('bootstrapValidator').destroy();
                _$form.data('bootstrapValidator', null);
                formValidator();
            }
        });

        //表单验证
        formValidator();

        function formValidator() {
            _$form.bootstrapValidator({
                message: 'This value is not valid',
                fields: {
                    lowAlarm1: {
                        message: '低报警验证失败',
                        validators: {
                            regexp: {
                                regexp: /^([0-9]+(\.\d+)?|0\.\d+)$/,
                                message: '低报警只能输入正浮点数'
                            }
                        }
                    },
                    lowAlarm2: {
                        message: '低低报警验证失败',
                        validators: {
                            regexp: {
                                regexp: /^([0-9]+(\.\d+)?|0\.\d+)$/,
                                message: '低低报警只能输入正浮点数'
                            }
                        }
                    },
                    highAlarm1: {
                        message: '高报警验证失败',
                        validators: {
                            regexp: {
                                regexp: /^([0-9]+(\.\d+)?|0\.\d+)$/,
                                message: '高报警只能输入正浮点数'
                            }
                        }
                    },
                    highAlarm2: {
                        message: '高高报警验证失败',
                        validators: {
                            regexp: {
                                regexp: /^([0-9]+(\.\d+)?|0\.\d+)$/,
                                message: '高高报警只能输入正浮点数'
                            }
                        }
                    },
                    color_Normal: {
                        message: '正常颜色验证失败',
                        validators: {
                            notEmpty: {
                                message: '正常颜色不能为空'
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
                    toastr.success('保存成功!');
                    _$form.data('bootstrapValidator').destroy();
                    _$form.data('bootstrapValidator', null);
                    formValidator();
                })
            }
            else {
                _modelService.update(
                    model, function (data) {
                        toastr.success('保存成功!');
                        _$form.data('bootstrapValidator').destroy();
                        _$form.data('bootstrapValidator', null);
                        formValidator();
                    }
                );
            }
        });
    });
})();
