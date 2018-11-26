/**
 * Created by BF100252 on 2016/7/8.
 */
var _base = {
    init:{
        // 初始化界面
        pageUI:function(){
            $("#easyuitree").tree({
                url: "/data/menu.json",
                dnd:true, // 是否启动拖拽功能
                animate:true, // 是否显示展开/折叠动画
                //checkbox:true,// 是否在每一个节点上显示复选框
                lines:true, // 是否显示树控件上的虚线
                onClick:function(node){
                    //console.log(node);
                    // $("#easyuitree").tree("getChildren",node)
                    var children = node.children; // 获取子节点
//                            console.log(children);

                    var tabs = $('#center_tabs').tabs("getTab", node.text);
                    //console.info(tabs);
                    if (tabs){
                        // 切换到指定面板
                        $('#center_tabs').tabs("select", node.text);
                    }else{
                        if (node.url != null && node.url != '' && node.url != undefined){
                            var content = '<iframe scrolling="auto" frameborder="0"  src="'+node.url+'" style="width:100%;height:100%;"></iframe>';
                            // 添加面板
                            $('#center_tabs').tabs('add',{
                                title:node.text,
                                //content:'Tab Body',
                                content:content,
                                closable:true
                            });
                        }
                    }
                }
            });
        },
        // 初始化需绑定的按钮
        bindEvent:function(){
            $("#eazyui-book").click(function () {
                $('#dlg').dialog('open').dialog('setTitle','个人信息');
                $('#fm').form('load','/kqs/getUser.do');
            });
            $("#eazyui-quit").click(function(){
                $.messager.confirm('确认','您确认想要退出吗?',function(r){
                    if (r){
                        $.messager.alert('操作提示', "退出成功!", 'info',function(){
                            window.location.href = '/logout.do'
                        });
                    }
                });
            });
        }
    }
}

function updateUser(){
    $('#fm').form('submit',{
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(data){
            var jsondata = null;//eval('(' + data + ')');
            if (data instanceof Object){
                jsondata = data;
            }else{
                jsondata = eval('(' + data + ')');
            }
            //console.log(jsondata);
           // console.log(jsondata.status);
            //console.log(jsondata.message);
            if (jsondata.status == 200){
                $.messager.alert('操作提示', jsondata.message, 'info',function(){
                    $('#dlg').dialog('close');
                });
            }else{
                $.messager.alert('操作提示', jsondata.message, 'error');
            }
        }
    });
}