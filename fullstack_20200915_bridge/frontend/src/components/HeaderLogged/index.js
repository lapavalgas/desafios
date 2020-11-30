import React from 'react';
import HeaderGithubLogo from '../HeaderGithubLogo';
import HeaderSearchEngine from '../HeaderSearchEngine';
import HeaderUserFavorites from '../HeaderUserFavorites';
import HeaderUserLogo from '../HeaderUserLogo';
import './style.css';

const HeaderLogged = (props) => {
    return (
        <div className="header">
            <div className="headerLeft">
                <HeaderGithubLogo />
                <div className="headerSearchFavorites">
                    <HeaderSearchEngine />
                    <HeaderUserFavorites />
                </div>
            </div>
            <div >
                <HeaderUserLogo />
            </div>
        </div>
    )
}

// div headerLogo : className="headerRight"

export default HeaderLogged;