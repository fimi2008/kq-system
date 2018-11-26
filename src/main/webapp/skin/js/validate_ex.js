/**
 * Created by lionxxw on 2016/7/8.
 * 扩展easyui 验证类型
 */
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '两次输入不一致!'
    },
    //验证汉子字
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字'
    },
    //移动手机号码验证
    mobile: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不准确.'
    },
    //国内邮编验证
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.'
    },
    //用户账号验证(只能包括 _ 数字 字母)
    account: {//param的值为[]中值
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    },
    selectValueRequired: {
        validator: function(value,param){
            if (value == "" || value.indexOf('请选择') >= 0) {
                return false;
            }else {
                return true;
            }
        },
        message: '该下拉框为必选项'
    },
    minLength : { // 判断最小长度
        validator : function(value, param) {
            return value.length >= param[0];
        },
        message : '最少输入 {0} 个字符'
    },
    length : { // 长度
        validator : function(value, param) {
            var len = $.trim(value).length;
            return len >= param[0] && len <= param[1];
        },
        message : "输入内容长度必须介于{0}和{1}之间"
    },
    phone : {// 验证电话号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '格式不正确,请使用下面格式:020-88888888'
    },
    phoneAndMobile : {// 电话号码或手机号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^(13|15|18)\d{9}$/i.test(value);
        },
        message : '电话号码或手机号码格式不正确'
    },
    idcard : {// 验证身份证
        validator : function(value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value) || /^\d{18}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message : '身份证号码格式不正确'
    },
    intOrFloat : {// 验证整数或小数
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '请输入数字，并确保格式正确'
    },
    currency : {// 验证货币
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '货币格式不正确'
    },
    qq : {// 验证QQ,从10000开始
        validator : function(value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message : 'QQ号码格式不正确'
    },
    integer : {// 验证整数
        validator : function(value) {
            return /^[+]?[1-9]+\d*$/i.test(value);
        },
        message : '请输入整数'
    },
    chinese : {// 验证中文
        validator : function(value) {
            return /^[\u0391-\uFFE5]+$/i.test(value);
        },
        message : '请输入中文'
    },
    chineseAndLength : {// 验证中文及长度
        validator : function(value) {
            var len = $.trim(value).length;
            if (len >= param[0] && len <= param[1]) {
                return /^[\u0391-\uFFE5]+$/i.test(value);
            }
        },
        message : '请输入中文'
    },
    english : {// 验证英语
        validator : function(value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message : '请输入英文'
    },
    englishAndLength : {// 验证英语及长度
        validator : function(value, param) {
            var len = $.trim(value).length;
            if (len >= param[0] && len <= param[1]) {
                return /^[A-Za-z]+$/i.test(value);
            }
        },
        message : '请输入英文'
    },
    englishUpperCase : {// 验证英语大写
        validator : function(value) {
            return /^[A-Z]+$/.test(value);
        },
        message : '请输入大写英文'
    },
    unnormal : {// 验证是否包含空格和非法字符
        validator : function(value) {
            return /.+/i.test(value);
        },
        message : '输入值不能为空和包含其他非法字符'
    },
    username : {// 验证用户名
        validator : function(value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
    faxno : {// 验证传真
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '传真号码不正确'
    },
    zip : {// 验证邮政编码
        validator : function(value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message : '邮政编码格式不正确'
    },
    ip : {// 验证IP地址
        validator : function(value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message : 'IP地址格式不正确'
    },
    name : {// 验证姓名，可以是中文或英文
        validator : function(value) {
            return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message : '请输入姓名'
    },
    engOrChinese : {// 可以是中文或英文
        validator : function(value) {
            return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message : '请输入中文'
    },
    engOrChineseAndLength : {// 可以是中文或英文
        validator : function(value) {
            var len = $.trim(value).length;
            if (len >= param[0] && len <= param[1]) {
                return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
            }
        },
        message : '请输入中文或英文'
    },
    carNo : {
        validator : function(value) {
            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
        },
        message : '车牌号码无效（例：粤B12350）'
    },
    carenergin : {
        validator : function(value) {
            return /^[a-zA-Z0-9]{16}$/.test(value);
        },
        message : '发动机型号无效(例：FG6H012345654584)'
    },
    same : {
        validator : function(value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message : '两次输入的密码不一致!'
    },
    checkAccount :{
        validator : function(value,param){
            var sysUser = {};
            var flag ;
            sysUser.account = value;
            $.ajax({
                url : '/checkAccount.do',
                type : 'POST',
                timeout : 60000,
                data:sysUser,
                async: false,
                success : function(data, textStatus, jqXHR) {
                    if (data == "0") {
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
            });
            if(flag){
                $("#account").removeClass('validatebox-invalid');
            }
            return flag;
        },
        message: '该账号已经被注册！'
    },
    checkMobile :{
        validator : function(value,param){
            var sysUser = {};
            var flag ;
            sysUser.account = value;
            $.ajax({
                url : '/checkMobile.do',
                type : 'POST',
                timeout : 60000,
                data:sysUser,
                async: false,
                success : function(data, textStatus, jqXHR) {
                    if (data == "0") {
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
            });
            if(flag){
                $("#account").removeClass('validatebox-invalid');
            }
            return flag;
        },
        message: '该手机号码已经被注册！'
    },
    checkEmail :{
        validator : function(value,param){
            var sysUser = {};
            var flag ;
            sysUser.account = value;
            $.ajax({
                url : '/checkEmail.do',
                type : 'POST',
                timeout : 60000,
                data:sysUser,
                async: false,
                success : function(data, textStatus, jqXHR) {
                    if (data == "0") {
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
            });
            if(flag){
                $("#account").removeClass('validatebox-invalid');
            }
            return flag;
        },
        message: '该邮箱已经被注册！'
    },
    radio: {
        validator: function (value, param) {
            var frm = param[0], groupname = param[1], ok = false;
            $('input[name="' + groupname + '"]', document[frm]).each(function () { //查找表单中所有此名称的radio
                if (this.checked) { ok = true; return false; }
            });

            return ok
        },
        message: '需要选择一项！'
    },
    checkbox: {
        validator: function (value, param) {
            var frm = param[0], groupname = param[1], checkNum = 0;
            $('input[name="' + groupname + '"]', document[frm]).each(function () { //查找表单中所有此名称的checkbox
                if (this.checked) checkNum++;
            });

            return checkNum > 0 && checkNum < 4;
        },
        message: '选择1~3项！'
    },
    requireRadio: {
        validator: function(value, param){
            var input = $(param[0]);
            input.off('.requireRadio').on('click.requireRadio',function(){
                $(this).focus();
            });
            return $(param[0] + ':checked').val() != undefined;
        },
        message: '需要选择一项{1}.'
    }
});