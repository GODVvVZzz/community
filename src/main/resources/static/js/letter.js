$(function(){
	$("#sendBtn").click(send_letter);
	$(".closemessage").click(delete_msg);
});

function send_letter() {
	$("#sendModal").modal("hide");

	var toName = $("#recipient-name").val();
	var content = $("#message-text").val();

	$.post(
		CONTEXT_PATH + "/letter/send",
		{"toName":toName,"content":content},
		function (data) {
			data = $.parseJSON(data);
			if (data.code == 0){
				$("#hintBody").text("发送成功！");
			}else {
				$("#hintBody").text("发送失败！");
			}

			$("#hintModal").modal("show");
			setTimeout(function(){
				$("#hintModal").modal("hide");
			}, 2000);
			location.reload();
		}
	)
}

function delete_msg() {
	// TODO 删除数据
	var btn = this;
	var id = $(btn).prev().val();

	$.post(
		CONTEXT_PATH + "/letter/delete",
		{"id":id},
		function (data) {
			data = $.parseJSON(data);
			if (data.code == 0){
				$(btn).parents(".media").remove();
			}else {
				alert("删除失败！");
			}
		}
	)


}