$('#levelDropdown').change(async function () {
    const url = $(this).data('api-subject-url') + "/" + $(this).val();
    const response = await getAnyData(url);
    $('#subjectDropdown').empty().append(getSubjectDropdowns(response.value));
});

$('#subjectDropdown').change(async function () {
    const url = $(this).data('api-chapter-url') + "/" + $(this).val();
    const response = await getAnyData(url);
    $('#chapterDropdown').empty().append(getChapterDropdowns(response.value));
});


