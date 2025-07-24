
seatGrid: boolean[][] = [];

ngOnInit() {
  const rows = 5;
  const cols = 10;

  this.seatGrid = Array.from({ length: rows }, () =>
    Array.from({ length: cols }, () => false)
  );
}
