export interface Customer {
  id: number
  firstname: string,
  lastname: string,
  email: string
}

export interface Page {
  page: number,
  size: number,
  totalPages: number
  totalElements: number
  content: any
}
