var testJSON = {id: '3'};
showReply(testJSON, function (data) {
        // document.getElementById("1").innerText="userId:"+data.data.userId;
        // document.getElementById("2").innerText="kindName:"+data.data.kindName;
        // document.getElementById("3").innerText="title:"+data.data.title;
        // document.getElementById("4").innerText="content:"+data.data.content;
        // var datetime=new Date(data.data.createdTime);
        // document.getElementById("5").innerText="createdTime:"+datetime.toLocaleDateString().replace(/\//g, "-")
        //     + " " + datetime.toTimeString().substr(0, 8);

        var pl = data.data;
        for (var i in pl) {
            document.write(pl[i].content);


        }
    }
);