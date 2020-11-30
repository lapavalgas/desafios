import React from 'react';
import HeaderLogged from '../../components/HeaderLogged';
import HeaderLoggout from '../../components/HeaderLoggout';
import ApiSearch from '../ApiSearch';
import './style.css';
import '../global.css';

function HomeStyle(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
        return <HeaderLogged />;
    }
    return <HeaderLoggout />;
}


function HomeMain(props) {
    return (
        <div className="App">
            <div className="appHeader">
                <HomeStyle isLoggedIn={true} />
            </div>
            <div className="appMain">
                <ApiSearch word={'github'} />
            </div>
        </div>
    );
}

export default HomeMain;