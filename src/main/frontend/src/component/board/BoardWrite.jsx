import React from "react";

export default function BoardWrite(props){

    return(
        <div>
            <h1>글 쓰기 페이지</h1>
            카테고리 이름:<input type="text" className="bcname"/>
            <button type="button" onclick="setbcategory()"> 카테고리등록</button>
            <span className="bcategorybox"></span>
            <form className="boardform">
                제목:<input type="text" name="btitle"/>
                내용:<input type="text" name="bcontent"/>
                첨부파일 :<input type="file" name="bfile"/>
                <button type="button" onclick="setboard()">글 등록</button>
        
        
            </form>
        </div>

    );
}