import React from "react";

export default function BoardList(props){
    return(
        <div>
            <a href="/board/write">글쓰기</a>
            <div className="bcategorybox"></div>
            <table className="boardlist"></table>
            
        </div>
    )
}