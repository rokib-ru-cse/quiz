function getSubjectDropdowns(fullList) {
    let tempText = "";
    let option = "<option value='' selected>Select Subject</option>";
    $.each(fullList, function (index, value) {
        if (tempText.indexOf(value.id) === -1 && value.id !== "") {
            option += "<option value='" + value.id + "'>" + value.subjectCode + " - " + value.name + "</option>";
            tempText += value.id + ",";
        }
    });
    return option;
}

function getChapterDropdowns(fullList) {
    let tempText = "";
    let option = "<option value='' selected>Select Chapter</option>";
    $.each(fullList, function (index, value) {
        if (tempText.indexOf(value.id) === -1 && value.id !== "") {
            option += "<option value='" + value.id + "'>" + value.chapterCode + " - " + value.name + "</option>";
            tempText += value.id + ",";
        }
    });
    return option;
}
