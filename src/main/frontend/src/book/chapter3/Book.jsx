import React  from "react";
function Book(props){
    fetch("/member/list")
        .then((response) => response.json())
        .then((data) => console.log(data));
    
    return(
        <div>
            <h1>이 책의 이름은 {props.name} 입니다.</h1>
            <h2>이 책은 총 {props.numOfPage} 페이지로 이루어져 있습니다</h2>
        </div>
    );
}
export default Book;