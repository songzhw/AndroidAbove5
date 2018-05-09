var exec = require("child_process").exec;

function query(resp){
	console.log("received 'query' request");
	function onFinish(error, stdout, stderr){
		resp.writeHead(200, {"Content-Type": "text/plain"});
		resp.write(stdout);
		resp.end();
	}
	exec("ls -lah", onFinish);
}

function upload(resp){
	console.log("received 'upload' request");
	resp.writeHead(200, {"Content-Type": "text/plain"});
	resp.write("Upload Something");
	resp.end();
}

exports.query = query;
exports.upload = upload;