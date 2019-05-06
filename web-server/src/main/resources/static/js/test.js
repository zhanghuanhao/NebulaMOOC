var testJSON = {id: '3'};
replyStar(testJSON, function (data) {

    if (data.code == 100) {
        toastr.success("好了");
    } else {
        toastr.warning("不行");
    }

    }
);