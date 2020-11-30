import React from 'react';
import HeaderLogged from '../../components/HeaderLogged';
import HeaderLoggout from '../../components/HeaderLoggout';
import RepositoryToList from '../RepositoryToList';
import './style.css';

function HomeStyle(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
        return <HeaderLogged />;
    }
    return <HeaderLoggout />;
}

function UserFavorites(props) {
    return (
        <div className="App">
            <div className="appHeader">
                <HomeStyle isLoggedIn={true} />
            </div>

            <div className="appMain">
                <RepositoryToList />
            </div>

        </div>
    );
}

export default UserFavorites;