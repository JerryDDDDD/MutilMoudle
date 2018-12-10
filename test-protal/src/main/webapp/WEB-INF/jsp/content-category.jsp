<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">  </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
//文档加载后触发
$(function(){
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,
		method : "GET",
        // 右击鼠标触发
		onContextMenu: function(e,node){
            e.preventDefault();
            //选中右击鼠标的节点
            $(this).tree('select',node.target);
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        // 选种放开时 编辑之后触发
        onAfterEdit : function(node){
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点
                //parentId:node.parentId,name:node.text
                //parentId:就是新增节点的父节点的Id
                //name:新增节点的文本
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        			    // 更新节点
        				_tree.tree("update",{
            				target : node.target,
            				id : data.data.id // 更新新增节点的Id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:node.id,name:node.text},function (data) {
                    $.messager.alert(data.status);
                });
        	}
        }
	});
});
function menuHandler(item){
    //获取树节点
	var tree = $("#contentCategory");
	//获取被选中的节点
	var node = tree.tree("getSelected");
	if(item.name === "add"){
	    // 在被点击的节点下追加一个子节点
		tree.tree('append', {
            parent: (node?node.target:null), // 被添加的子节点的父节点
            data: [{
                text: '新建分类', //节点的内容
                id : 0,          //节点的id
                parentId : node.id
            }]
        }); 
		var _node = tree.tree('find',0);//找到ID为0的节点
        // 选择ID为0的节点（要添加的节点）
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){
				$.post("/content/category/delete/",{id:node.id},function(){
					tree.tree("remove",node.target);
				});	
			}
		});
	}
}
</script>