export interface IPageableResponse<T> {
    content: T[],
    empty: boolean,
    first: boolean,
    last: boolean,
    number: number,
    size: number,
    totalElements: number,
    totalPages: Number
}