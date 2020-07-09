<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
//	ajax file upload Start
function uploadFile(schoolId) {
	var status = "";
	
	  $.ajax({
	    url: "/fileUpload",
	    type: "POST",
	    data: new FormData($("#uploadForm")[0]),
	    enctype: 'multipart/form-data',
	    processData: false,
	    contentType: false,
	    cache: false,
	    success: function () {
	    	status = "true";
	    },
	    error: function () {
	    	status = "false";
	    }
	});
	return status;
}
//ajax file upload End 
</script>
file upload
<form id="uploadForm">
	<div class="row">
		<div class="file-field input-field height2">
			<div class="col s1 " align="left">
				<span></span><input type="file" id="logoUpload"
					name="logoUpload" accept="*.txt">
			</div>
			<div class="file-path-wrapper">
				<input class="file-path" id="fileLogo" type="text"
					placeholder="Choose School Logo... *">
			</div>
			
		</div>
	</div>

</form>
<span onclick = "uploadFile()">upload Btn</span>


 <img src="/resources/IMG-20190218-WA0003.jpg" alt="Girl in a jacket" width="500" height="600"> 
 
 
 