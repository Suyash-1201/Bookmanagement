import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/admin.css';

export default function Adminpage() {
  const nav = useNavigate();

  return (
    <div className="admin-container">
      <header className="admin-header">
        <h1>Admin Panel</h1>
        <p>Library Management</p>
      </header>

      <div className="admin-grid">
        {/* Books Box */}
        <div 
          className="admin-card" 
          tabIndex="0" 
          onClick={() => nav("/list")}
          onKeyDown={(e) => e.key === 'Enter' && nav("/list")}
        >
          <div className="card-icon">📚</div>
          <div className="card-content">
            <h3>Books</h3>
            <p>Manage library inventory</p>
          </div>
        </div>

        {/* Borrowers Box */}
        <div 
          className="admin-card" 
          tabIndex="0" 
          onClick={() => nav("/borrow")}
          onKeyDown={(e) => e.key === 'Enter' && nav("/borrow")}
        >
          <div className="card-icon">🤝</div>
          <div className="card-content">
            <h3>Borrowers</h3>
            <p>Track issued items</p>
          </div>
        </div>

        {/* Users Box */}
        <div 
          className="admin-card" 
          tabIndex="0" 
          onClick={() => nav("/user")}
          onKeyDown={(e) => e.key === 'Enter' && nav("/user")}
        >
          <div className="card-icon">👤</div>
          <div className="card-content">
            <h3>Users</h3>
            <p>Manage accounts</p>
          </div>
        </div>

        {/* Admin Signup Box */}
        <div 
          className="admin-card admin-highlight" 
          tabIndex="0" 
          onClick={() => nav("/adminsave")}
          onKeyDown={(e) => e.key === 'Enter' && nav("/adminsave")}
        >
          <div className="card-icon">🔐</div>
          <div className="card-content">
            <h3>Admin Signup</h3>
            <p>Register new admin (/adminsave)</p>
          </div>
        </div>
      </div>
    </div>
  );
}