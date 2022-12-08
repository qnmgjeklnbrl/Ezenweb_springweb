// p.279 : 컴포넌트
import React from 'react'
import Styles from './Toolbar.css'

export default function Toolbar(props) {
    const { isLoggedIn, onClickLogin, onClickLogout } = props;
    return (
        <div className="wrapper">

            { isLoggedIn && <span className="greeting"> 환영합니다 </span>}

            { isLoggedIn ? (
                <button onClick={onClickLogout}> 로그아웃 </button>
            ) : (
                <button onClick={onClickLogin}> 로그인 </button>
            ) }

        </div>
    )
}