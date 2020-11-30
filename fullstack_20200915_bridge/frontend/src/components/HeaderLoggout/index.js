import React from 'react';
import HeaderGithubLogo from '../HeaderGithubLogo';
import HeaderSearchEngine from '../HeaderSearchEngine';
import HeaderSignin from '../HeaderSignin';
import './style.css';

const HeaderLoggout = (props) => {
    return (
        <div className="header">
            <div className="headerLeft">
                <HeaderGithubLogo />
                <div className="headerSearchFavorites">
                    <HeaderSearchEngine />
                </div>
            </div>
            <div className="headerRight">
                <HeaderSignin />
            </div>
        </div>
    )
}
export default HeaderLoggout;