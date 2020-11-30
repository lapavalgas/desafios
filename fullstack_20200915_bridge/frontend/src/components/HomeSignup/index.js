import React from 'react';
import HeaderLogged from '../../components/HeaderLogged';
import HeaderLoggout from '../../components/HeaderLoggout';
import FormSignup from '../FormSignup';
import './style.css';

function HomeStyle(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
        return <HeaderLogged />;
    }
    return <HeaderLoggout />;
}

function HomeSignup(props) {
    return (
        <div className="App">
            <div className="appHeader">
                <HomeStyle isLoggedIn={true} />
            </div>

            <div className="appMain">
                <FormSignup />
            </div>

        </div>
    );
}

export default HomeSignup;