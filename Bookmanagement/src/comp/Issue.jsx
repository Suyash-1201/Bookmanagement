import axios from 'axios';
import React, { useEffect, useState } from 'react';

const Issue = () => {
  const [getdata, setGetdata] = useState([]);
  const [issue, setIssue] = useState({ sname: '', number: '', bname: '' });
  const [showModal, setShowModal] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  // Fetch issued records
  async function fetchData() {
    try {
      const res = await axios.get("http://localhost:8080/manage/getissue");
      setGetdata(res.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  }

  useEffect(() => {
    fetchData();
  }, []);

  // Handle form field changes
  function handleChange(e) {
    const { name, value } = e.target;
    setIssue(prev => ({
      ...prev,
      [name]: value
    }));
  }

  // Submit form data
  async function handleSubmit() {
    // Basic validation check
    if (!issue.sname || !issue.number || !issue.bname) {
      setErrorMessage("Please fill in all input fields.");
      return;
    }

    try {
      setErrorMessage('');
      await axios.post("http://localhost:8080/manage/saveissue", issue);
      setIssue({ sname: '', number: '', bname: '' }); // Clear form
      fetchData(); // Refresh list
    } catch (error) {
      console.error("Error saving data:", error);
      setErrorMessage("Failed to submit issue data.");
    }
  }

  return (
    <div className="container">
      <div className="card">
        <h2 className="title">Issue Book</h2>

        {errorMessage && (
          <div className="error-alert">
            {errorMessage}
          </div>
        )}

        <div className="form-group">
          <div className="input-field-group">
            <label htmlFor="sname">Register Name</label>
            <input
              id="sname"
              type="text"
              className="input-field"
              value={issue.sname || ''}
              name="sname"
              placeholder="Enter register name"
              onChange={handleChange}
            />
          </div>

          <div className="input-field-group">
            <label htmlFor="number">Contact / Roll Number</label>
            <input
              id="number"
              type="number"
              className="input-field"
              value={issue.number || ''}
              name="number"
              placeholder="Enter number"
              onChange={handleChange}
            />
          </div>

          <div className="input-field-group">
            <label htmlFor="bname">Book Name</label>
            <input
              id="bname"
              type="text"
              className="input-field"
              value={issue.bname || ''}
              name="bname"
              placeholder="Enter book name"
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="button-group">
          <button className="btn btn-primary" onClick={handleSubmit}>
            Submit
          </button>
          <button 
            className="btn btn-secondary" 
            onClick={() => setIssue({ sname: '', number: '', bname: '' })}
          >
            Clear
          </button>
        </div>

        <div className="view-all-wrapper">
          <button 
            className="btn btn-outline" 
            onClick={() => setShowModal(true)}
          >
            View All Issued ({getdata.length})
          </button>
        </div>
      </div>

      {/* Modal Popup for List Visualizations */}
      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h3>Issued Books List</h3>
              <button className="close-btn" onClick={() => setShowModal(false)}>
                &times;
              </button>
            </div>

            <div className="modal-body">
              {getdata.length === 0 ? (
                <p className="no-data">No issue records found.</p>
              ) : (
                <div className="student-list">
                  {getdata.map((item, index) => (
                    <div key={item.id || index} className="student-item">
                      <div className="avatar-img" />
                      <div className="student-info">
                        <h4>{item.sname}</h4>
                        <p>
                          <span>No: {item.number} | Book: {item.bname}</span>
                        </p>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Issue;
