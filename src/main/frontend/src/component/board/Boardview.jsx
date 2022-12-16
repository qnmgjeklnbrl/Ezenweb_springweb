import React from 'react';
import {
    HashRouter,
    BrowserRouter,
    Routes,
    Route,
    Link,
    Router ,
    useParams // 라우터 경로상의 매개변수 호출 훅 [ 쿼리스트링 형식 ]
    }
from "react-router-dom";

export default function BoardView( props ){
    const params = useParams();
    return(
        <div>
            뷰 페이지로 들어옴 페이지번호 : { params.bno  }
        </div>
    )
}
