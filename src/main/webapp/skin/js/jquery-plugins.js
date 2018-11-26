/**
 * jquery
 * 核心 (选择器, dom操作, 事件, ajax)
 * 插件:
 *      全局方法的插件
 *      $.extend({
 *      })
 *
 *      局部方法的插件
 *      $.fn.extend({
 *      })
 * UI : easyUI(它是基于jquery的一个扩展)
 *
 */
$.fn.extend({
    datagrid: function (obj) {
        var $table = $(this);
        var colums = obj.columns;
        var tr = "<tr>";
        for (var i = 0; i < colums.length; i++) {
            tr+="<td>"+colums[i].title+"</td>";
        }
        tr += "</tr>";
        // this 一般是指代dom对象,我们可以把它包装成jquery对象
        $table.append(tr);

        $.ajax({
            url:obj.url,
            type:obj.method,
            success:function(data){
                var rows = data.rows;
                for (var i = 0; i < rows.length; i++) {
                    tr = "<tr>";
                    tr+="<td>"+rows[i].no+"</td>";
                    tr+="<td>"+rows[i].startstate+"</td>";
                    tr+="<td>"+rows[i].starttime+"</td>";
                    tr+="<td>"+rows[i].swz+"</td>";
                    tr+="<td>"+rows[i].tdz+"</td>";
                    tr+="<td>"+rows[i].note+"</td>";
                    tr += "</tr>";
                    //alert(tr);
                    $table.append(tr);
                }
            }
        });
    }
});