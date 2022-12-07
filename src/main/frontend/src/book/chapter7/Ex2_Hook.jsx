import React,{useState,useEffect} from "react";

export default function UserStatus(props){
    const[isOnline, setIsOnline] = useState(null);
    useEffect( () => {
        function handleStateChanhe(state){
            setIsOnline(state.isOnline);
        }
        
        ServerAPI.subscribeUserStatus(props.user.id , handleStateChanhe);
        return() => {ServerAPI.unsubscribeUserStatus(props.user.id , handleStateChanhe);}
    })
    if(isOnline==null){return"대기중";}
    return isOnline ? "온라인":"오프라인";
}
