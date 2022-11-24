
const name ='소플';
c


function formatName(user){
    return user.fisrtName + '  '+user.lastName;



}

const user = {fisrtName:'Inje',lastName:'Lee'};

const element2 = (
    <h1>

        Hello,{formatUser(user)}

    </h1>


);
ReactDOM.render(
    element,
    document.getElementById('root')
);
