import React, { useEffect, useState } from 'react';
import '../css/borrower.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Borrower = () => {
  // Initialized state to array instead of object
  const [getdata, setgetdata] = useState([]);
  
  const nav = useNavigate();

  async function data() {
    try {
      const res = await axios.get("http://localhost:8080/manage/getissue");
      setgetdata(res.data);
    } catch (error) {
      console.log(error);
    }
  }

  async function update(id) {
    try {
        const res=await axios.put(`http://localhost:8080/manage/updatestatus/${id}`)
        data();
    } catch (error) {
        console.log(error)
    }
  }

  useEffect(() => {
    data();
  }, []);

  return (
    <div className="borrower-container">
      {/* Header section matching top toolbar */}
      <div className="borrower-header">
        <h2>Borrower Records</h2>
        <button className="issue-btn" onClick={() => nav("/issue")}>
          📖 Issue Books
        </button>
      </div>

      {/* Table section */}
      <div className="table-responsive">
        <table className="issue-table">
          <thead>
            <tr>
              <th>#</th>
              <th>Book Name</th>
              <th>User Name</th>
              <th>Issue Date</th>
              <th>Return Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {getdata && getdata.length > 0 ? (
              getdata.map((item, index) => (
                <tr key={index}>
                  <td className="id-column">{index + 1}</td>
                  <td>{item.book?.bname}</td>
                  <td>{item.stud?.sname}</td>
                  <td>{item.issuedate}</td>
                  <td>{item.returndate}</td>
                  <td onClick={()=>update(item.issueid)}>
                    <span onClick={()=>update(item.issueid)} className={`status-badge ${item.status ? 'returned' : 'issued'}`}>
                      {item.status ? "Returned" : "Issued"}
                    </span>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6" className="no-data">
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

export default Borrower;