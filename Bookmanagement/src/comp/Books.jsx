import React, { useState } from 'react';
import '../css/books.css';
import axios from 'axios';

const Books = () => {
    const [data, setdata] = useState({ bname: '', stock: '' });
    const [fetchdata, setfetchdata] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);

    function handel(e) {
        const { name, value } = e.target;
        setdata(n => ({
            ...n,
            [name]: value
        }));
    }

    async function fetch() {
        try {
            const res = await axios.get("http://localhost:8080/manage/getbooks");
            setfetchdata(res.data);
            setIsModalOpen(true);
        } catch (e) {
            console.log(e);
        }
    }

    async function save() {
        if (!data.bname || !data.stock) {
            alert("Please fill in all fields.");
            return;
        }
        try {
            await axios.post("http://localhost:8080/manage/savebook", data);
            alert("Book data stored successfully");
            setdata({
                bname: "",
                stock: ""
            });
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="books-container">
            <div className="card-box">
                <h2 className="title">Book Management</h2>
                <div className="form-group">
                    <input 
                        type="text" 
                        name="bname" 
                        value={data.bname || ''} 
                        placeholder="Enter new book name" 
                        onChange={handel} 
                        className="input-field"
                    />
                    <input 
                        type="number" 
                        name="stock" 
                        value={data.stock || ''} 
                        placeholder="Enter stock" 
                        onChange={handel} 
                        className="input-field"
                    />
                </div>
                <div className="button-group">
                    <button onClick={save} className="btn btn-primary">Add Book</button>
                    <button onClick={fetch} className="btn btn-secondary">Show Books</button>
                </div>
            </div>

            {/* Modal Popup for Books Table */}
            {isModalOpen && (
                <div className="modal-overlay" onClick={() => setIsModalOpen(false)}>
                    <div className="modal-content" onClick={e => e.stopPropagation()}>
                        <div className="modal-header">
                            <h3>Book List</h3>
                            <button className="close-btn" onClick={() => setIsModalOpen(false)}>&times;</button>
                        </div>
                        <div className="table-wrapper">
                            <table className="books-table">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Book Name</th>
                                        <th>Stock Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {fetchdata && fetchdata.length > 0 ? (
                                        fetchdata.map((item, index) => (
                                            <tr key={index}>
                                                <td>{index + 1}</td>
                                                <td>{item.bname || item.name || 'N/A'}</td>
                                                <td>{item.stock}</td>
                                            </tr>
                                        ))
                                    ) : (
                                        <tr>
                                            <td colSpan="3" className="no-data">No books found</td>
                                        </tr>
                                    )}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Books;