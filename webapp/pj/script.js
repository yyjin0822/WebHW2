/**
 * 
 */
/**
 * 
 */
function insertBtn() {
	var con = document.getElementById('Ginsert');
	con.style.display = 'block';
	document.getElementById('GList').style.display = 'none';
}
function reset() {
	document.getElementById('user').value = null;
	document.getElementById('email').value = null;
	document.getElementById('title').value = null;
	document.getElementById('pw').value = null;
	document.getElementById('content').value = null;
}

function checkForm() {
	if (
    document.getElementById('user').value == '' ||
    document.getElementById('email').value == '' ||
    document.getElementById('title').value == '' ||
    document.getElementById('pw').value == '' ||
    document.getElementById('content').value == ''
  	) {
        alert( '모든 입력란을 채워주세요' );
        return false;
    }
}