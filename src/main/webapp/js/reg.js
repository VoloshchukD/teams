function checkTextArea(){
    const regex = new RegExp(document.getElementById("textAreaRegex").value);
    if(regex.test(document.getElementById("skills-description").value) == false){
        document.getElementById("skills-description").focus();
        return false;
    }
    return true;
}