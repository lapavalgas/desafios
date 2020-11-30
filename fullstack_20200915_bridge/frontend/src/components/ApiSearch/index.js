import React from 'react';
import RepositoryToList from '../RepositoryToList';


const BASE_URL = 'http://localhost:8082/search/';

class ApiSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            list: [],
        }
    }
    componentDidMount(props) {
        this.search(props);
    }

    search(props) {
        function status(response) {
            if (response.status >= 200 && response.status < 300) {
                return Promise.resolve(response)
            } else {
                return Promise.reject(new Error(response.statusText))
            }
        }

        function json(response) {
            // return JSON.parse(response.request.body.stringify());
            return response.JSON()
        }


        fetch(BASE_URL + this.props.word)
            .then(status)
            .then(json)
            .then((data) => {
                this.setState({ list: data })
                console.log(typeof (this.state.list))
            })
            .then(function (data) {
                console.log('Request succeeded with JSON response', typeof(data));
            })
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }

    render() {
        return (
            <div className="App">

                {/* {this.state.list.map(
                    repository => (
                        <div>
                            { <RepositoryToList repository={repository} />}
                        </div>

                    ))} */}
            </div>
        );
    }
}

export default ApiSearch;