//get any list section start
async function getAnyData(url, jsObject) {
    try {
        const response = await $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            data: {stringObject: JSON.stringify(jsObject)}
        });
        return response;
    } catch (err) {
        throw err;
    }
}

//!get any list section end

//post any data section start
function postAnyData(url, jsObject) {
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        beforeSend: function () {
        },
        data: {stringObject: JSON.stringify(jsObject)},
        success: function (response) {
            return response;
        },
        error: function (err) {
            return err;
        }
    });
}

//!post any data section end


//get any list section start
function getAnyDataAsync(url, jsObject) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            data: {stringObject: JSON.stringify(jsObject)},
            success: function (data) {
                resolve(data);
            },
            error: function (err) {
                //please make sure your controller path is right and handle the exception in the catch block inside controller
                reject(err);
            }
        });
    });
}

//!get any list section end

//post any data section start
function postAnyDataAsync(url, jsObject) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            beforeSend: function () {
            },
            data: {stringObject: JSON.stringify(jsObject)},
            success: function (data) {
                resolve(data)
            },
            error: function (err) {
                //please make sure your controller path is right and handle the exception in the catch block inside controller
                reject(err);

            },
        });
    });
}

//!post any data section end