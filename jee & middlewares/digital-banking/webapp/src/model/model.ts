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

export interface Operation {
  id: number,
  amount: number,
  type: string
  description: string
  createdAt: string
  target: string
}

export interface BankAccount {
  id: string,
  status: string
  type: string,
  balance: number,
  createdAt: string
  customer: Customer
  operations: Operation[]
  interestRate: number, // for saving account
  overDraft: number // for current account
}
