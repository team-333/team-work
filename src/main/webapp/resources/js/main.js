function getContextPath() {
        var hostIndex = location.href.indexOf( location.host ) + location.host.length;
        return location.href.substring( hostIndex, location.href.indexOf("/", hostIndex + 1) );
      }

   function check(node,text){
      return node.indexOf(text,0);
   }
   
   
   function searchstudy(){
      text= document.getElementById('searchtext').value;   //검색할 텍스트
      list =document.getElementById('listparent');      // ul의 리스트 노드
      let child = list.childNodes;
      console.log("list : "+list);   
      for(i=1;i<child.length;i+=2){      // 리스트 초기화 시키기
         child[i].hidden=false;      
         }
      if(text==="") return;         // 검색텍스트를 입력 안했으면 함수 종료
      for(i=1;i<child.length;i+=2){
         console.log(i+'번째 텍스트'+child[i].innerText);
         let node = child[i].innerText;
         if(check(node,text)===-1){
            child[i].hidden=true;
         }
      }
}