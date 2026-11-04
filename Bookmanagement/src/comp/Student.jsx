import React, { useEffect, useState } from 'react';
import '../css/student.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Student = () => {
    const [stud, setstud] = useState({ sname: '', number: '' });
    const [getdata, setgetdata] = useState([]);
    const [text, settext] = useState("");
    const [find, setfind] = useState(false);
    const [isModalOpen, setIsModalOpen] = useState(false);

    const nav = useNavigate();

    function handel(e) {
        const { name, value } = e.target;
        setstud(prev => ({
            ...prev,
            [name]: value
        }));
        if (find) setfind(false);
    }

    async function fetch() {
        try {
            const res = await axios.get("http://localhost:8080/manage/getstudents");
            setgetdata(res.data);
        } catch (error) {
            console.log("Error fetching students:", error);
        }
    }

    useEffect(() => {
        fetch();
    }, []);

    async function dataset() {
        if (!stud.sname || !stud.number) {
            setfind(true);
            settext("Please fill in all fields");
            return;
        }

        const exist = getdata.some(s =>
            String(s.number) === String(stud.number)
        );

        if (exist) {
            setfind(true);
            settext("User already exists with this mobile number");
            return;
        }

        try {
            await axios.post("http://localhost:8080/manage/savestud", stud);
            alert("Student registered successfully");
            setstud({ sname: "", number: "" });
            setfind(false);
            fetch();
        } catch (error) {
            console.log("Error saving student:", error);
        }
    }

    async function deletestud(id) {
        try {
            const res= await axios.delete(`http://localhost:8080/manage/delete/student/${id}`)
            fetch()
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div className="container">
            <div className="card">
                <h2 className="title">Student Registration</h2>

                <div className="form-group">
                    <div className="input-field-group">
                        <label>Student Name</label>
                        <input 
                            type="text" 
                            name="sname" 
                            className="input-field"
                            value={stud.sname || ''} 
                            placeholder="Enter student name" 
                            onChange={handel} 
                        />
                    </div>

                    <div className="input-field-group">
                        <label>Mobile Number</label>
                        <input 
                            type="number" 
                            name="number" 
                            className="input-field"
                            value={stud.number || ''} 
                            placeholder="Enter mobile number" 
                            onChange={handel} 
                        />
                    </div>
                </div>

                {find && (
                    <div className="error-alert">
                        <p>{text}</p>
                    </div>
                )}

                <div className="button-group">
                    <button className="btn btn-primary" onClick={dataset}>
                        Submit
                    </button>
                    <button className="btn btn-secondary" onClick={() => nav("/issue")}>
                        Issue Books
                    </button>
                </div>

                <div className="view-all-wrapper">
                    <button className="btn btn-outline" onClick={() => setIsModalOpen(true)}>
                        👥 View All Students ({getdata.length})
                    </button>
                </div>
            </div>

            {/* Modal for All Students List */}
            {isModalOpen && (
                <div className="modal-overlay" onClick={() => setIsModalOpen(false)}>
                    <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                        <div className="modal-header">
                            <h3>Registered Students List</h3>
                            <button className="close-btn" onClick={() => setIsModalOpen(false)}>&times;</button>
                        </div>

                        <div className="modal-body">
                            {getdata.length === 0 ? (
                                <p className="no-data">No students registered yet.</p>
                            ) : (
                                <div className="student-list">
                                    {getdata.map((student, idx) => (
                                        <div key={student.id || idx} className="student-item">
                                            <img 
                                                src={`https://api.dicebear.com/7.x/bottts/svg?seed=${student.sname || idx}`} 
                                                alt="avatar" 
                                                className="avatar-img"
                                            />
                                            <div className="student-info">
                                                <h4>{student.sname || 'Unnamed Student'}</h4>
                                                <p>📞 {student.number} <span><img src="../img/trash-bin.png" alt="" onClick={()=>deletestud(student.sid)} /></span></p>
                                                
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

export default Student;
