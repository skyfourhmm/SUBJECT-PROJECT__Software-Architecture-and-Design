function BillsHeader({ searchQuery, setSearchQuery }) {
  return (
    <div className="flex justify-between items-center mb-6">
      <h1 className="text-xl font-semibold">Bill List</h1>
      <div className="flex gap-4">
        <input
          type="text"
          placeholder="Search bills..."
          className="pl-10 pr-4 py-2 border rounded-lg w-64"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
      </div>
    </div>
  );
}

export default BillsHeader;
