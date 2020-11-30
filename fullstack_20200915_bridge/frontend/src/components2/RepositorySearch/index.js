import React from 'react';
import './style.css';


const RepositorySearch = (props) => {
    return (
        <div class="repositorySearch">
            <h1>79,536 repository results for "snake"</h1>
            <form action="/action_page.php">
                <select name="cars" id="cars">
                    <optgroup label="Order by...">
                        <option value="saab">Best match</option>
                        <option value="saab">Most stars</option>
                        <option value="mercedes">Fewest Stars</option>
                        <option value="audi">Recently updated</option>
                        <option value="audi">Least recently updated</option>
                    </optgroup>
                </select>
            </form>
        </div>
    )
}

export default RepositorySearch;