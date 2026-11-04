import React, { useEffect, useState } from 'react';
import '../css/user.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Booklist = () => {
  const [getdata, setgetdata] = useState([]);
  
  const nav = useNavigate();

  async function data() {
    try {
      const res = await axios.get("http://localhost:8080/manage/getbooks");
      setgetdata(res.data);
    } catch (error) {
      console.log(error);
    }
  }

  async function deletestud(id) {
    try {
      const res = await axios.delete(`http://localhost:8080/manage/deletebook/${id}`);
      data();
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    data();
  }, []);

  return (
    <div className="user-container">
      {/* Header section matching top toolbar */}
      <div className="user-header">
        <h2>Book Record</h2>
        <button className="add-btn" onClick={() => nav("/book")}>
          📖 Add Books
        </button>
      </div>

      {/* Table section */}
      <div className="table-responsive">
        <table className="user-table">
          <thead>
            <tr>
              <th>#</th>
              <th>Book Name</th>
              <th>Stock</th>
              <th className="action-header">Action</th>
            </tr>
          </thead>
          <tbody>
            {getdata && getdata.length > 0 ? (
              getdata.map((item, index) => (
                <tr key={index}>
                  <td className="id-column">#{index + 1}</td>
                  <td className="name-column">{item.bname}</td>
                  <td>{item.stock}</td>
                  <td className="action-cell">
                    <button className="delete-btn" onClick={() => deletestud(item.bid)} title="Delete Student">
                      <img src="../img/trash-bin.png" alt="Delete" className="delete-icon" />
                    </button>

                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4" className="no-data">
                  No records found
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Booklist;
