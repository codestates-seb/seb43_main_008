export interface PostData {
  id: number;
  title: string;
  isPublic: boolean;
  isEditable: boolean;
  isActive: boolean;
  seriesStatus: string;
  image: string;
  daylogCount?: number;
  createdAt?: string;
  modifiedAt?: string | null;
  voteCount?: number;
  voteResult?: any | null;
  voteAgree?: number;
  voteDisagree?: number;
  revoteResult?: any | null;
  revoteAgree?: number;
  revoteDisagree?: number;
  type?: string
}

export interface ProfileData {
  nickName: string;
  image: string;
  introduce: string;
  followedMember?: boolean
}