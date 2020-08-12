const addHashTagEnter = (event) => {
        if (event.keyCode === 13) {
          const inputForm = document.getElementById("hashTagForm");
          addHashTag(inputForm.value);
        }
      };

      const addHashTag = (context) => {
        const hashTagList = document.getElementById("hashTag-wrapper");
        console.log(context);
        // hashTag
        const newHash = document.createElement("div");
        newHash.className = "hashTag";
        // hashTag_context
        const newHash_value = document.createElement("div");
        newHash_value.className = "hashTag__value";
        newHash_value.innerText = context;
        newHash.appendChild(newHash_value);
        // hashTag_input[hidden]
        const input_hidden = document.createElement("input");
        input_hidden.type = "hidden";
        input_hidden.className = "hashTag__context";
        input_hidden.name = "hashTag";
        input_hidden.value = context;
        newHash.appendChild(input_hidden);
        // deleteBtn
        const deleteBtn = document.createElement("div");
        deleteBtn.className = "deleteHashTag";
        deleteBtn.id = "deleteHashTag";
        deleteBtn.innerText = "X";
        deleteBtn.addEventListener("click", function () {
          this.parentNode.remove();
        });
        newHash.appendChild(deleteBtn);

        // input initialize
        const inputForm = document.getElementById("hashTagForm");
        const new_input = inputForm;
        new_input.value = "";
        inputForm.remove();

        hashTagList.appendChild(newHash);
        hashTagList.appendChild(new_input);
      };